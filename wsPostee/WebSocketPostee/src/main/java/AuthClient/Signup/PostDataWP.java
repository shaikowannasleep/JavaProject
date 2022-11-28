/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthClient.Signup;

import AuthClient.Age;

/**
 *
 * @author Hoang Quoc Bao
 */
public class PostDataWP {
    public String address;
    public String firstName;
    public String lastName;
    public Age age;
    public String gender;
    public int job; //1 --> marketing, 2 ----> developer, 3 ----> hr
    public Object jobDescription;
    public String username;
    public String email;
    public String phoneNumber;
    public PostDataWP(PostData data)
    {
        this.username = data.username;
        this.phoneNumber = data.phoneNumber;
        this.lastName = data.lastName;
        this.gender = data.gender;
        this.firstName = data.firstName;
        this.email = data.email;
        this.age = data.age;
        this.address = data.address;
        this.job = data.job;
        this.jobDescription = data.jobDescription;
    }
    public PostDataWP()
    {
        // Do Event
    }
}
