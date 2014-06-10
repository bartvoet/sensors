package org.sensor.agent;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorAgentRuntime {

	@Autowired
	private SensorAgent agent;
	
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("agent-context.xml");
	}
	
	@Scheduled(fixedDelay=15000)
	public void meassure() throws IOException, InterruptedException{
		agent.meassure();
	}
	
}
