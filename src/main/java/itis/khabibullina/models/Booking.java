package itis.khabibullina.models;

import java.sql.Date;

public class Booking {
    private int id;
    private int hall;
    private int programId;
    private Date date;

    public Booking(int id, int hall, int programId, Date date) {
        this.id = id;
        this.hall = hall;
        this.programId = programId;
        this.date = date;
    }

    public Booking(int hall, int programId, Date date) {
        this.hall = hall;
        this.programId = programId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
