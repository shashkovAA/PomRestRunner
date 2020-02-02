package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    Logger log;

    public MyLogger() {
        log = LogManager.getLogger("Run");
    }
}
