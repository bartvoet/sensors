package org.sensor.agent;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.b.v.sensors.api.Sensor;
import org.b.v.sensors.sensirion.SHT21OverI2C;
import org.sensor.agent.dependencies.SensorConfiguration;
import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorLogger;
import org.sensor.agent.dependencies.SensorRegistry;
import org.sensor.agent.dependencies.support.DefaultSensorRegistry;
import org.sensor.agent.dependencies.support.EmptySensorEvents;
import org.sensor.agent.dependencies.support.EmptySensorLogger;
import org.sensor.agent.dependencies.support.InMemorySensorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Handles requests for the application home page.
 */
@Component
public class SensorAgent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorAgent.class);
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private org.b.v.system.System system;
	private SensorRegistry registry=new DefaultSensorRegistry();
	private SensorConfiguration configuration=new InMemorySensorConfiguration();
	private SensorLogger logger=new EmptySensorLogger();
	private SensorEvents events=new EmptySensorEvents();
	
	public SensorAgent() throws IOException{}
	
	@PostConstruct
	private void after() throws IOException{
		registry=new DefaultSensorRegistry().addSensor("1", new SHT21OverI2C(system.createI2CConnection(0x40)));
	}
	
	@Scheduled(fixedDelay=15000)
	public void filldatabase() throws IOException, InterruptedException{
		logger.debug("read sensors");
		
		for(Sensor sensor : registry.activeSensors()){
			for(String type : sensor.type().getTypeNames()) {
				Map<String,Object> parameters = new HashMap<String,Object>();
				parameters.put("sensorId", "1");
				parameters.put("measurementTime", new Date());
				parameters.put("functionality", type);
				parameters.put("measurementValue", sensor.meassure(type));
				jdbcTemplate.update("insert into SensorMeassurement "
						+ "(sensorId,measurementTime,functionality,measurementValue) "
						+ "values(:sensorId,:measurementTime,:functionality,:measurementValue)"
						, parameters);
			}
		}
		
	}
	
	public void setSystem(org.b.v.system.System system) {
		this.system = system;
	}

	public void setRegistry(SensorRegistry registry) {
		this.registry = registry;
	}

	public void setConfiguration(SensorConfiguration configuration) {
		this.configuration = configuration;
	}

	public void setLogger(SensorLogger logger) {
		this.logger = logger;
	}

	public void setEvents(SensorEvents events) {
		this.events = events;
	}
		
}
