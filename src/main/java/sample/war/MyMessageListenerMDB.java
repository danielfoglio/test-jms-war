package sample.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by danielfoglio on 12/14/16.
 */
@MessageDriven(name = "MyMessageListenerMDB",
        activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
        ,@ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic")
        ,@ActivationConfigProperty(propertyName = "destinationJndiName",
                propertyValue = "jms/notificationsJmsDistributedTopic")
        ,@ActivationConfigProperty(propertyName = "subscriptionDurability",
                propertyValue = "Durable")
        ,@ActivationConfigProperty(propertyName  = "connectionFactoryJndiName",
                propertyValue = "jms/notificationsJmsConnectionFactory")
        ,@ActivationConfigProperty(propertyName  = "topicMessagesDistributionMode",
                propertyValue = "One-Copy-Per-Application")
        ,@ActivationConfigProperty(propertyName  = "jmsClientId",
                propertyValue = "MyJMSClientId")
        ,@ActivationConfigProperty(propertyName  = "subscriptionName",
                propertyValue = "MysubscriptionName")
        ,@ActivationConfigProperty(propertyName  = "distributedDestinationConnection",
                propertyValue = "EveryMember")
})
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MyMessageListenerMDB implements MessageListener, MessageDrivenBean {

    @Resource
    MessageDrivenContext messageDrivenContext;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                if (text.length() > 80) {
                    text = text.substring(0, 80) + "...";
                }
                logger.info("MDB onMessage - Text Message from: " +text+ " On: "+SampleWarApplication.SERVER_NAME);
            } else {
                logger.info("MDB onMessage - Message from: " +message+ " On: "+SampleWarApplication.SERVER_NAME);
            }
        } catch (JMSException e) {
            throw new RuntimeException("Could not log message", e);
        }
    }

    @Override
    public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) throws EJBException {
        this.messageDrivenContext = messageDrivenContext;
    }

    @Override
    public void ejbRemove() throws EJBException {
        logger.info("\n\nejbRemove called ...\n\n");
    }
}
