package org.sensor.agent.dependencies.support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSensorEvents implements SensorEvents {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		
		DateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		String message = measurement.getId() 
							+ ";" 
							+ format.format(measurement.getDate())
							+ ";"
							+ measurement.getType()
							+ ";"
							+ measurement.getValue().getValue();
		
		jmsTemplate.convertAndSend("sensor_in", message);
	}

}