package org.wulin.activemq.example.consumer2.demo1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer2TopicDemo1 {
	
	public static void main(String[] args) throws Exception {
		
		Consumer2TopicDemo1 consumer = new Consumer2TopicDemo1();
		consumer.TestTopicConsumer();
	}
	
	
	 public void TestTopicConsumer() throws Exception{
		 	//failover:(tcp://192.168.0.49:61616) //当网络出现断网时,过段时间有好了,能自动重连
//		    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("failover:("+value+")");
//		    factory.setTrustAllPackages(true); // 信任所有的包
		 
	        //1、创建工厂连接对象，需要制定ip和端口号
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://wulinThinkPad:61616");
	        //2、使用连接工厂创建一个连接对象
	        Connection connection = connectionFactory.createConnection();
	        connection.setClientID("test-topic2");
	        //3、开启连接
	        connection.start();
	        //4、使用连接对象创建会话（session）对象
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
	        Topic topic = session.createTopic("test-topic");
	        
	        //6、使用会话对象创建生产者对象
	        MessageConsumer consumer = session.createConsumer(topic);
//	        TopicSubscriber consumer = session.createDurableSubscriber(topic, "test-topic1");
	        //7、向consumer对象中设置一个messageListener对象，用来接收消息
	        consumer.setMessageListener(new MessageListener() {

	            @Override
	            public void onMessage(Message message) {
	                // TODO Auto-generated method stub
	                if(message instanceof TextMessage){
	                    TextMessage textMessage = (TextMessage)message;
	                    try {
	                        System.out.println(textMessage.getText());
	                    } catch (JMSException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                }
	            }
	        });
	        //8、程序等待接收用户消息
	        System.in.read();
	        //9、关闭资源
	        consumer.close();
	        session.close();
	        connection.close();
	    }

}
