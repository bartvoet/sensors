package org.sensor.agent;


import java.io.IOException;
import java.util.Date;

import org.b.v.sensor.agent.configuration.api.SensorConfiguration;
import org.b.v.sensor.agent.configuration.api.SensorConfigurationRepository;
import org.b.v.sensor.agent.configuration.memory.MemorySensorConfigurationRepository;
import org.b.v.sensors.api.Sensor;
import org.b.v.values.SensorValue;
import org.b.v.values.SensorValueDefinition;
import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorLogger;
import org.sensor.agent.dependencies.SensorMeasurement;
import org.sensor.agent.dependencies.SensorRegistry;
import org.sensor.agent.dependencies.support.DefaultSensorRegistry;
import org.sensor.agent.dependencies.support.EmptySensorEvents;
import org.sensor.agent.dependencies.support.EmptySensorLogger;

public class SensorAgent {
	
	private SensorRegistry registry=new DefaultSensorRegistry();
	
	private SensorConfigurationRepository configuration=new MemorySensorConfigurationRepository();
	private SensorLogger logger=new EmptySensorLogger();
	private SensorEvents events=new EmptySensorEvents();
	
	public SensorAgent(SensorRegistry registry) {
		this.registry=registry;
	}
	
	
	
	public void meassure() throws IOException, InterruptedException{
		logger.debug("read sensors");
		
		for(Sensor sensor : registry.activeSensors()){
			for(SensorValueDefinition type : sensor.type().getMeasurementTypes()) {
				applyNewConfigurationIfAvailable(sensor);
				SensorValue value = sensor.meassure(type.getName());
				events.pushMeasurement(
						new SensorMeasurement(
							sensor.identification(),type.getName(),new Date(),value
						));
			}
		}
	}

	private void applyNewConfigurationIfAvailable(Sensor sensor) {
		SensorConfiguration configuration = events.getNewConfiguraiton(sensor.identification());
		if(configuration!=null) {
			this.configuration.newConfigurationForSensor(sensor.identification(), configuration);
			sensor.configure(configuration.getValues());
		}
	}
	

	public void setRegistry(SensorRegistry registry) {
		this.registry = registry;
	}

	public void setLogger(SensorLogger logger) {
		this.logger = logger;
	}
	
	public void setEvents(SensorEvents events) {
		this.events = events;
	}
		
}
