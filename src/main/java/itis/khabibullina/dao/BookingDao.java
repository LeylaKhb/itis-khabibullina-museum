package itis.khabibullina.dao;

import java.sql.Date;
import java.util.List;

public interface BookingDao<T> extends Dao<T>{
    int getBookingsCount(int programId, Date date, int hall);
}
