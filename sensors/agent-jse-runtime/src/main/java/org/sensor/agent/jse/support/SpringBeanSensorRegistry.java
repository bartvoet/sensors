package org.sensor.agent.jse.support;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.b.v.sensors.api.Sensor;
import org.sensor.agent.dependencies.SensorRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component("sensors")
public class SpringBeanSensorRegistry implements BeanFactoryPostProcessor,SensorRegistry {

	Map<String, Sensor> sensors = new HashMap<String,Sensor>();
	

	@Override
	public Sensor getSensor(String id) {
		return sensors.get(id);
	}

	@Override
	public Collection<Sensor> activeSensors() {
		return sensors.values();
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
			throws BeansException {
		sensors = factory.getBeansOfType(Sensor.class);
	}
}
