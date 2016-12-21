package sample.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController{

    Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private MyMessageSender myMessageSenderMDB;

	@RequestMapping("/")
	public String hello() throws Exception{
		return "Hello World!";
	}

    @RequestMapping("/sendmdb")
    public String sendMDB() throws Exception{
        for (int i = 0; i < 100; i++) {
            logger.info("Sending message "+ i +" from: " + SampleWarApplication.SERVER_NAME);
            myMessageSenderMDB.send(i + " " + SampleWarApplication.SERVER_NAME);
        }
        return "MDB Message sent";
    }

}
