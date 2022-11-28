/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mac
 */
public class Age {
    private int day;
    private int month;
    private int year;

    public Age(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public Object setDay(int day) {
        this.day = day;
        return null;
    }

    public int getMonth() {
        return month;
    }

    public Object setMonth(int month) {
        this.month = month;
        return null;
    }

    public int getYear() {
        return year;
    }

    public Object setYear(int year) {
        this.year = year;
        return null;
    }

    @Override
    public String toString() {
        return "age: {" +
                "day : " + day +
                ", month : " + month +
                ", year : " + year +
                '}';
    }
}
