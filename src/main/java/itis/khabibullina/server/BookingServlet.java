package itis.khabibullina.server;

import itis.khabibullina.dao.BookingDao;
import itis.khabibullina.dao.Dao;
import itis.khabibullina.dao.impl.BookingDaoImpl;
import itis.khabibullina.dao.impl.ExhibitionDaoImpl;
import itis.khabibullina.dao.impl.ProgramDaoImpl;
import itis.khabibullina.dto.ExhibitionDto;
import itis.khabibullina.models.Booking;
import itis.khabibullina.models.Exhibition;
import itis.khabibullina.models.Program;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "bookingServlet", urlPatterns = "/booking")
public class BookingServlet extends HttpServlet {
    private final BookingDao<Booking> bookingDao = new BookingDaoImpl();
    private final Dao<Program> programDao = new ProgramDaoImpl();
    private final Dao<ExhibitionDto> exhibitionDao = new ExhibitionDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        List<ExhibitionDto> exhibitions = exhibitionDao.getAll();
        req.setAttribute("exhibitions", exhibitions);

        String hall = req.getParameter("hall");
        String program = req.getParameter("program");
        String date = req.getParameter("date");

        if (hall != null && program != null && date != null) {
            if (!canBeBooked(hall, program, date)) {
                resp.setContentType("text/plain");
                resp.getWriter().write(program + " in hall " + hall + " is full. Please, pick another program or exhibition");
            }
        } else {
            req.getRequestDispatcher("main.ftl").forward(req, resp);
        }
        
    }

    private boolean canBeBooked(String hall, String program, String date) {
        Date wantedDate = Date.valueOf(date);

        ExhibitionDto exhibition = exhibitionDao.get(hall);
        Date startDate = exhibition.getStartDate();
        Date endDate = exhibition.getEndDate();
        if (startDate.compareTo(wantedDate) <= 0 && endDate.compareTo(wantedDate) <= 0) {
            Program programByName = programDao.get(program);
            int programId = programByName.getId();
            int count = bookingDao.getBookingsCount(programId, wantedDate, Integer.parseInt(hall));
            if (count < programByName.getPeopleCount()) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int hall = Integer.parseInt(req.getParameter("hall"));
        String programName = req.getParameter("program");
        Date date = Date.valueOf(req.getParameter("date"));

        Program program = programDao.get(programName);

        bookingDao.save(new Booking(hall, program.getId(), date));
    }
    
    
}
