package org.sensor.agent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SensorAgentRuntime {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("agent-context.xml");
//		SensorAgent agent = context.getBean(SensorAgent.class);
	}
}
