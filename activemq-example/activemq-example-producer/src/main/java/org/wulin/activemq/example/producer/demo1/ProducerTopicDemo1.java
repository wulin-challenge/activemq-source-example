package org.wulin.activemq.example.producer.demo1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTopicDemo1 {
	
	public static void main(String[] args) throws Exception {
		ProducerTopicDemo1 provider = new ProducerTopicDemo1();
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
	        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
	        Topic topic = session.createTopic("test-topic");
	        //6、使用会话对象创建生产者对象
	        MessageProducer producer = session.createProducer(topic);
	        //7、使用会话对象创建一个消息对象
	        TextMessage textMessage = session.createTextMessage("hello!test-topic");
	        //8、发送消息
	        producer.send(textMessage);
	        //9、关闭资源
	        producer.close();
	        session.close();
	        connection.close();
	    }


}
