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
public class MarkertingClient extends Job{
    public int yearExperience;
    public Boolean isKnowFacebookAds;
    public MarkertingClient()
    {
        this.name = "Marketing";
    }

    public int getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(int yearExperience) {
        this.yearExperience = yearExperience;
    }

    public Boolean getKnowFacebookAds() {
        return isKnowFacebookAds;
    }

    public void setKnowFacebookAds(Boolean knowFacebookAds) {
        isKnowFacebookAds = knowFacebookAds;
    }
}
