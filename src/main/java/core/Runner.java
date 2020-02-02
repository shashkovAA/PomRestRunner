package core;


import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
/**
 * @Autor Shashkov A.
 */
public class Runner extends MyLogger{
    private Properties epmProperties = new Properties();

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start(args);
    }

    private void start(String [] args) {
        log.info("Start application ...");

        File propFile = new File("epm.properties");
        loadProperties(propFile);

        String ipAddress = epmProperties.getProperty("epm.ip.address");
        String login = epmProperties.getProperty("epm.ws.login");
        String password = epmProperties.getProperty("epm.ws.password");

        RestRepo repo = new RestRepo(ipAddress, login, password);

        repo.httpGETCampaignJobDetails().forEach(c-> System.out.println(c.toString()));

    }

    private void loadProperties(File file) {

        try (FileInputStream input = new FileInputStream(file);
             Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"))){
            epmProperties.load(reader);
            log.debug("EPM config file [" + file.getName() + "] loaded successfully...");

        } catch (IOException except) {
            log.error(except.getMessage());
        }
    }


}
