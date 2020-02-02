package core;

import model.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestRepoTest {

    RestRepo repo;

    @BeforeEach
    void setUp() {
        repo = new RestRepo();
    }

    @Test
    void parseCampaignList() {
        String jsonString ="[{\"campaignName\":\"testCampaign\",\"jobID\":42,\"jobState\":\"JOB_ACTIVE\"}]";
        Campaign campaign = new Campaign();
        campaign.setCampaignName("testCampaign");
        campaign.setJobID("42");
        campaign.setJobState("JOB_ACTIVE");

        assertEquals(campaign, repo.parseCampaignJobDetails(jsonString).get(0));
    }




}