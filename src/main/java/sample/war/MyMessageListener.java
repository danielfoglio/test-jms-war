package sample.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by danielfoglio on 12/13/16.
 */

public class MyMessageListener{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void onMessage(String message) {
        logger.info("Spring onMessage from: "+ message +" On: "+SampleWarApplication.SERVER_NAME);
    }

}
