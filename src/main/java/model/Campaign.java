package model;

import java.util.Objects;

public class Campaign {
    private String campaignName;
    private String jobID;
    private String jobState;

    public Campaign() {
    }

    public Campaign(String campaignName, String jobID, String jobState) {
        this.campaignName = campaignName;
        this.jobID = jobID;
        this.jobState = jobState;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignName='" + campaignName + '\'' +
                ", jobID='" + jobID + '\'' +
                ", jobState='" + jobState + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;
        Campaign campaign = (Campaign) o;
        return campaignName.equals(campaign.campaignName) &&
                jobID.equals(campaign.jobID) &&
                jobState.equals(campaign.jobState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignName, jobID, jobState);
    }
}
