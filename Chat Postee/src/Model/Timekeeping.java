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
public class Timekeeping {
    private String date;
    private String checkin;
    private String checkout;
    private double workday;

    public Timekeeping(String date, String checkin, String checkout, double workday) {
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;
        this.workday = workday;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public double getWorkday() {
        return workday;
    }

    public void setWorkday(double workday) {
        this.workday = workday;
    }
}
