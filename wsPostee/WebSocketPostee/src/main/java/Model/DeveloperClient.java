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
public class DeveloperClient extends Job{
    public String platform;
    public int yearExperience;
    public Boolean isKnowSpofityPlatform;
    public DeveloperClient()
    {
        this.name = "Developer";
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(int yearExperience) {
        this.yearExperience = yearExperience;
    }

    public Boolean getKnowSpofityPlatform() {
        return isKnowSpofityPlatform;
    }

    public void setKnowSpofityPlatform(Boolean knowSpofityPlatform) {
        isKnowSpofityPlatform = knowSpofityPlatform;
    }
}
