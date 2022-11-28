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
public class RegisterData {


    private String firstName;
    private String lastName;
    private Age age;
    private String gender;
    private String username;
    private int job;
    private int pos;
    private Object jobDescription;
    private int yearExperience;
    private Boolean isKnow;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;

    public RegisterData(String firstName,
                        String lastName,
                        Age age,
                        String gender,
                        String username,
                        int job,
                        int pos,
                        String email,
                        String phoneNumber,
                        String password,
                        String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.username = username;
        this.job = job;
        this.pos = pos;
        switch (job) {
        case 1:
            MarkertingClient mk = new MarkertingClient();
            mk.description = "Marketing fb";
            mk.setPosition(pos);
            this.jobDescription = mk;
            switch (pos){
                case 1:
                    mk.name = "Marketing Staff";
                    break;
                case 2:
                    mk.name = "Marketing Manager";
                    break;
                case 3:
                    mk.name = "Marketing Director";
            }
            mk.setYearExperience(yearExperience);
            mk.setKnowFacebookAds(isKnow);
            break;
        case 2:
            DeveloperClient dev = new DeveloperClient();
            dev.description = "Developer";
            dev.setPosition(pos);
            this.jobDescription = dev;
            switch (pos){
                case 1:
                    dev.name = "Developer Staff";
                    break;
                case 2:
                    dev.name = "Developer Manager";
                    break;
                case 3:
                    dev.name = "Developer Director";
            }
            dev.setYearExperience(yearExperience);
            dev.setKnowSpofityPlatform(isKnow);
            break;
        case 3:
            HrClient hr = new HrClient();
            hr.description = "Human Resource";
            this.jobDescription = hr;
            hr.setPosition(pos);
            switch (pos){
                case 1:
                    hr.name = "Human Resource Staff";
                    break;
                case 2:
                    hr.name = "Human Resource Manager";
                    break;
                case 3:
                    hr.name = "Human Resource Director";
            }
            break;
        default:
            break;
    }
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public String getJD() {
        return (String) jobDescription;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    @Override
    public String toString() {
        return "RegisterData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                ", job=" + job +
                ", pos=" + pos +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}