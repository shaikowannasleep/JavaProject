/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrm.postee;

import javax.websocket.Session;

/**
 *
 * @author ADMIN
 */
public class Temp {
    private static String authorization;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String gender;
    private static String phoneNumber;
    private static String password;
    private static String address;
    private static String id;
    private static Double salary;
    private static String job;
    private static String pos;
    private static int day;
    private static int month;
    private static int year;
    private static String username;
    private static String type;
    private static String DOB;
    private static String code;
    private static Session session;
    private static String mode;

    public static String getMode() {
        return mode;
    }

    public static void setMode(String mode) {
        Temp.mode = mode;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Temp.session = session;
    }

    public static String getFirstName() {
            return firstName;
    }

    public static void setFirstName(String firstName) {
            Temp.firstName = firstName;
    }

    public static String getLastName() {
            return lastName;
    }

    public static void setLastName(String lastName) {
            Temp.lastName = lastName;
    }

    public static String getGender() {
            return gender;
    }

    public static void setGender(String gender) {
            Temp.gender = gender;
    }

    public static String getUser() {
            return username;
    }

    public static void setUser(String username) {
            Temp.username = username;
    }

    public static String getEmail() {
            return email;
    }

    public static void setEmail(String email) {
            Temp.email = email;
    }

    public static String getPhoneNumber() {
            return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
            Temp.phoneNumber = phoneNumber;
    }

    public static String getAddress() {
            return address;
    }

    public static void setAddress(String address) {
            Temp.address = address;
    }

    public static String getId() {
            return id;
    }

    public static void setId(String id) {
            Temp.id = id;
    }

    public static int getDay() {
            return day;
    }

    public static void setDay(int day) {
            Temp.day = day;
    }

    public static int getMonth() {
            return month;
    }

    public static void setMonth(int month) {
            Temp.month = month;
    }

    public static int getYear() {
            return year;
    }

    public static void setYear(int year) {
            Temp.year = year;
    }

    public static Double getSalary() {
            return salary;
    }

    public static void setSalary(Double salary) {
            Temp.salary = salary;
    }

    public static String getAuthorization() {
            return authorization;
    }

    public static void setAuthorization(String authorization) {
            Temp.authorization = authorization;
    }

    public static String getPass() {
            return password;
    }

    public static void setPass(String password) {
            Temp.password = password;
    }

    public static String getType() {
            return type;
    }

    public static void setType(String type) {
            Temp.type = type;
    }

    public static String getJob() {
            return job;
    }

    public static void setJob(String job) {
            Temp.job = job;
    }

    public static String getPos() {
            return pos;
    }

    public static void setPos(String pos) {
            Temp.pos = pos;
    }
    
    public static String getCode() {
            return code;
    }

    public static void setCode(String code) {
            Temp.code = code;
    }
}
