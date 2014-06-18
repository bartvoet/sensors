package org.sensor.agent.jse.support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.b.v.sensor.agent.configuration.api.SensorConfiguration;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("events")
public class ActiveMQSensorEvents implements SensorEvents {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		//TODO add configuration-id
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

	
	@Override
	public SensorConfiguration getNewConfiguraiton(String sensorId) {
		//factory.createJsonParser("").readValueAsTree().
		//configuration-id - sensor - list of values
		//jmsTemplate.receiveSelected(destinationName, messageSelector)
		return null;//TODO to implement
		
	}
	
//	public static void main(String[] args) {
//		JsonFactory factory = new MappingJsonFactory();
//		factory.createJsonParser(
//				
//				
//				);
//	}

}
