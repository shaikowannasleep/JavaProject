/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class CheckIn {

    public String checkin;
    public int exception;
    public double total;
    private int day;
    private int month;
    private int year;
    private String id;

    public CheckIn(String checkin, int exception, double total, int day, int month, int year, String id) {
        this.checkin = checkin;
        this.exception = exception;
        this.total = total;
        this.day = day;
        this.month = month;
        this.year = year;
        this.id = id;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public int getException() {
        return exception;
    }

    public void setException(int exception) {
        this.exception = exception;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CheckIn{" + "checkin=" + checkin + ", day=" + day + ", month=" + month + ", year=" + year + ", id=" + id + '}';
    }
    
}
