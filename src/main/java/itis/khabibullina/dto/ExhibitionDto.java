package itis.khabibullina.dto;

import java.sql.Date;

public class ExhibitionDto {
    private Date startDate;
    private Date endDate;
    private String name;
    private Integer hall;

    public ExhibitionDto(Date startDate, Date endDate, String name, Integer hall) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.hall = hall;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }
}
