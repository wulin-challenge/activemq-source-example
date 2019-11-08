package org.wulin.activemq.example.producer.demo1;
import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.OpenDataException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;

public class QueryTest {

	public static void main(String[] args) throws IOException, MalformedObjectNameException, NullPointerException, InstanceNotFoundException, MBeanException, ReflectionException, OpenDataException {
		// TODO Auto-generated method stub

		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");

		JMXConnector connector = JMXConnectorFactory.connect(url, null);

		connector.connect();

		MBeanServerConnection connection = connector.getMBeanServerConnection();

		ObjectName name = new ObjectName("org.apache.activemq:BrokerName=localhost,Type=Broker");

		BrokerViewMBean mbean =(BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection, name, BrokerViewMBean.class, true);

		System.out.println("Statistics for broker " + mbean.getBrokerId()+ " - " + mbean.getBrokerName());

		System.out.println();
		
		System.out.println("Number of unacknowledged messages on the broker: " + mbean.getTotalMessageCount());
		
		System.out.println("Number of messages that have been acknowledged on the broker: " + mbean.getTotalDequeueCount());
		
		System.out.println("Number of messages that have been sent to the broker: " + mbean.getTotalEnqueueCount());

		System.out.println("Number of message consumers subscribed to destinations on the broker: " + mbean.getTotalConsumerCount());
		
		System.out.println("Total number of Queues: " + mbean.getQueues().length);

		 

		for (ObjectName queueName : mbean.getQueues()) 

		{

		QueueViewMBean queueMbean = (QueueViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class,true);

		System.out.println();
		
		System.out.println("Statistics for queue " + queueMbean.getName());

		System.out.println("number of messages which are yet to be consumed: " + queueMbean.getQueueSize());

		System.out.println("Number of consumers subscribed this destination: " + queueMbean.getConsumerCount());
		
		
		CompositeData[] messages = queueMbean.browse();
		
		System.out.println();
		
		System.out.println("----------------------");
		
		for (int i = 0; i < messages.length; i++) {
			
			CompositeData data = messages[i];
			
			String value = "";
			
			if(data.containsKey("Text")) { 
			
				value = (String) data.get("Text");
			
			} else if(data.containsKey("ContentMap")) {
			
				value = (String) data.get("ContentMap");
			
			}
			
			System.out.println(value);
		
		}
		
		}
		
	}

}