/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthClient;

/**
 *
 * @author Hoang Quoc Bao
 */
public class UserProfile {
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String address;
    public Age age;
    public AuthData data;
    public UserProfile(String username, String firstName, String lastName, String email, String phoneNumber, String address, Age age, AuthData data)
    {
        this.username=username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.data = data;
    }
}
