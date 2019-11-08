package activemq.producer;

import activemq.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangfz on 2017/3/19.
 */
public class TestProducer extends BaseJunit4Test {

    @Autowired
    private ActiveMQProducer activeMQProducer;

    @Test
    public void send(){
        this.activeMQProducer.sendMessage("the message come from Spring!");
    }
}
