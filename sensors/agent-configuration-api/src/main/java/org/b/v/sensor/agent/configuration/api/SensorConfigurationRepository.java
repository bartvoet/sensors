package org.b.v.sensor.agent.configuration.api;


public interface SensorConfigurationRepository {
	public boolean containsConfigurationForSensor(String sensorId);
	public void newConfigurationForSensor(String sensorId,SensorConfiguration configuration);
	public SensorConfiguration currentConfiguration(String sensorId) throws NoConfigurationAvailable;
}
