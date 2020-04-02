package org.wulin.activemq.example.producer.demo1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 发送多个主题消息
 * @author ThinkPad
 *
 */
public class Producer22TopicDemo2 {
	
	public static void main(String[] args) throws Exception {
		Producer22TopicDemo2 provider = new Producer22TopicDemo2();
		provider.TestTopicProducer();
	}
	
	 public void TestTopicProducer() throws Exception{
		 	//failover:(tcp://192.168.0.49:61616) //当网络出现断网时,过段时间有好了,能自动重连
//		    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("failover:("+value+")");
//		    factory.setTrustAllPackages(true); // 信任所有的包
		 
	        //1、创建工厂连接对象，需要制定ip和端口号
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://wulinThinkPad:61616");
	        //2、使用连接工厂创建一个连接对象
	        Connection connection = connectionFactory.createConnection();
	        //3、开启连接
	        connection.start();
	        //4、使用连接对象创建会话（session）对象
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        
	        //发布主题1
	        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
	        Topic topic = session.createTopic("test-topic11");
	        //6、使用会话对象创建生产者对象
	        MessageProducer producer = session.createProducer(topic);
	        //7、使用会话对象创建一个消息对象
	        TextMessage textMessage = session.createTextMessage("hello!test-topic11");
	        //8、发送消息
	        producer.send(textMessage);
	        
	      //发布主题2
	        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
	        Topic topic2 = session.createTopic("test-topic22");
	        //6、使用会话对象创建生产者对象
	        MessageProducer producer2 = session.createProducer(topic2);
	        //7、使用会话对象创建一个消息对象
	        TextMessage textMessage2 = session.createTextMessage("hello!test-topic22");
	        //8、发送消息
	        producer2.send(textMessage2);
	        
	        
	        //9、关闭资源
	        producer.close();
	        session.close();
	        connection.close();
	    }


}
