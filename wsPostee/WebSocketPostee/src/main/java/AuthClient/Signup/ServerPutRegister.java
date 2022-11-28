/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthClient.Signup;

/**
 *
 * @author Hoang Quoc Bao
 */
public class ServerPutRegister extends PostData{
    public String id;
    public String createTime;
    public String type;
    public long salary;
    public String fullName;
    public ServerPutRegister(PostData data)
    {
        this.username = data.username;
        this.password = data.password;
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
}
