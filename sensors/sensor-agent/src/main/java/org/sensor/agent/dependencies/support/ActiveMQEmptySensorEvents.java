package org.sensor.agent.dependencies.support;

import java.util.HashMap;
import java.util.Map;

import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQEmptySensorEvents implements SensorEvents {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("sensorId",measurement.getId());
		parameters.put("measurementTime", measurement.getDate());
		parameters.put("functionality", measurement.getType());
		parameters.put("measurementValue", measurement.getValue().getValue());
		jdbcTemplate.update("insert into SensorMeassurement "
				+ "(sensorId,measurementTime,functionality,measurementValue) "
				+ "values(:sensorId,:measurementTime,:functionality,:measurementValue)"
				, parameters);
		
		String message = measurement.getId() 
							+ ";" 
							+ measurement.getDate()
							+ ";"
							+ measurement.getType()
							+ ";"
							+ measurement.getValue().getValue();
		
		jmsTemplate.convertAndSend("sensor_in", message);
	}

}
