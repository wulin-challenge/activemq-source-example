package activemq.consumer;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by zhangfz on 2017/3/19.
 */
public class MsgQueueMessageListener  implements SessionAwareMessageListener<Message> {

    @Override
    public void onMessage(Message message, Session session) throws JMSException {

        if(message instanceof TextMessage){

            System.out.println("consumer get msg : " + ((TextMessage) message).getText());

        }

    }

}
