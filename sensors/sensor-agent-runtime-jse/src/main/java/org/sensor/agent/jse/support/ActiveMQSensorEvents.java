package org.sensor.agent.jse.support;

import org.sensor.agent.dependencies.SensorAgentEvents;
import org.sensor.foundation.mapper.SensorMeasurementMapper;
import org.sensor.foundation.model.SensorConfiguration;
import org.sensor.foundation.model.SensorMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("events")
public class ActiveMQSensorEvents implements SensorAgentEvents {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private SensorMeasurementMapper measurementMapper=new SensorMeasurementMapper();
	
	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		//TODO add configuration-id
		String message = measurementMapper.mapToJson(measurement);
		jmsTemplate.convertAndSend("sensor_in", message);
	}


	@Override
	public SensorConfiguration getNewConfiguration(String sensorId) {
		//factory.createJsonParser("").readValueAsTree().
		//configuration-id - sensor - list of values
		//jmsTemplate.receiveSelected(destinationName, messageSelector)
		return null;//TODO to implement
		
	}
	

}
