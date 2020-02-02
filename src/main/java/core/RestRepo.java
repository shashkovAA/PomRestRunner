package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Campaign;
import model.FilterTemplate;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestRepo extends MyLogger{

    private String epmIp;
    private String login;
    private String password;

    public RestRepo() {
    }

    public RestRepo(String epmIp, String login, String password) {
        this.epmIp = epmIp;
        this.login = login;
        this.password = password;
    }

    public List<Campaign> httpGETCampaignJobDetails()  {

        String pomUrlString = "https://" + epmIp + "/VP_POM_Service/v3/campaigns/joblist/";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic(login, password);
        clientConfig.register(auth);

        Client client = ClientBuilder.newClient( clientConfig );
        WebTarget webTarget = client.target(pomUrlString);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).header("X-Requested-With", "rest").header("Cache-Control", "no-cache");
        Response response;
        try {
            response = invocationBuilder.get();

            log.debug("Status response code:" + response.getStatus());

            if(response.getStatus() == 200) {

                String jsonString = response.readEntity(String.class);

                return parseCampaignJobDetails(jsonString);
            }
            else {
                log.error("Some went wrong! " + response.getStatusInfo().getReasonPhrase());
                return new ArrayList<>();
            }
        }
        catch (Exception except) {
            log.error(except.getMessage());
            return new ArrayList<>();
        }
    }

    List<Campaign> parseCampaignJobDetails(String jsonString) {

        List<Campaign> campaignList = new ArrayList<>();

        Campaign[] campaigns = new Campaign[0];

        ObjectMapper mapper = new ObjectMapper();
        try {
            campaigns = mapper.readValue(jsonString, Campaign[].class);
            campaignList = Arrays.asList(campaigns);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return campaignList;
    }



    public void httpPOST() {

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic("appadmin", "appadmin01");
        clientConfig.register(auth);
        //clientConfig.register(TemplateFilter.class);
        FilterTemplate tFilter = new FilterTemplate();
        tFilter.setFilterName("Test_Filter");
        //tFilter

        Client client = ClientBuilder.newClient( clientConfig );
        WebTarget webTarget = client.target("https://172.23.36.237/VP_POM_Service/v3/campaigns/joblist/");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).header("X-Requested-With", "rest").header("Cache-Control", "no-cache");
        Response response = invocationBuilder.post(Entity.entity(tFilter, MediaType.APPLICATION_JSON));

        System.out.println(response.getStatus());

        if(response.getStatus() == 200)
        {
            System.out.println("OK");

            String jsonString = response.readEntity(String.class);

            Campaign[] campaigns = new Campaign[0];

            ObjectMapper mapper = new ObjectMapper();
            try {
                campaigns = mapper.readValue(jsonString, Campaign[].class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Campaign campaign : campaigns) {
                System.out.println(campaign.toString());
                if (campaign.getCampaignName().equals("Test_infinite")) {
                    System.out.println("JobId = " + campaign.getJobID());
                }

            }

            //String campaigns = response.readEntity(String.class);
            //List<Campaign> list = campaigns.getCampaignList();
            //campaigns.getCampaignList().forEach(c-> System.out.println(c.toString()));

        }
    }



}
