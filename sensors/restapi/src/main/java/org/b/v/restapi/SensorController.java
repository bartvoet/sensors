package org.b.v.restapi;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.b.v.sensors.sensirion.SHT21OverI2C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SensorController {
	
	@Autowired
	private org.b.v.system.System system;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorController.class);
	
	
	public SensorController() throws IOException{
	}

	private SHT21OverI2C sensor() throws IOException {
		return new SHT21OverI2C(system.createI2CConnection(0x40));
	}
	
	@RequestMapping(value = "/sensors", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<String> sensors() throws IOException, InterruptedException {
		LOGGER.info("List of sensors");
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		return list;

	}

	@RequestMapping(value = "/sensors/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> sensorValues(@PathVariable Integer id) throws IOException, InterruptedException {
		LOGGER.info("Sensor values");
		SHT21OverI2C sensor = sensor();
		Map<String,String> map = new HashMap<String,String>();
		map.put("humidity",Double.toString(sensor.readHumidity()));
		map.put("temperature",Double.toString(sensor.readTemperature()));
		return map;
		
	}

	@RequestMapping(value = "/sensors/{id}/overview", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Double>> sensorValuesOverview(@PathVariable Integer id) throws IOException, InterruptedException {
		LOGGER.info("Sensor values");
		Map<String,Object> parameters = new HashMap<String,Object>();
		return jdbcTemplate.query("select * from sensor_metrics", parameters, 
				new RowMapper<Map<String,Double>>(){
					@Override
					public Map<String,Double> mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Map<String,Double> result = new HashMap<String,Double>();
						result.put("humidity", rs.getDouble("humidity"));
						result.put("temperature", rs.getDouble("temperature"));
						return result;
					}
				}
			);
	}

	
//	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.GET)
//	@ResponseBody
//	public Team teamDetails(@PathVariable Integer teamId) {
//		LOGGER.info("Team-data");
//		return 
//		template.queryForObject("select * from teams where teamid=?", TEAM_MAPPER,teamId);
//	}
	
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Scheduled(fixedDelay=15000)
	public void filldatabase() throws IOException, InterruptedException{
		SHT21OverI2C sensor = sensor();
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("humidity",sensor.readHumidity());
		parameters.put("temperature",sensor.readTemperature());
		
		jdbcTemplate.update("insert into sensor_metrics "
				+ "(humidity,temperature) values(:humidity,:temperature)", parameters);
	}
	
	
	
//	
//	create table sensor_metrics (
//			metric_timestamp time,
//			humidity double,
//			temperature double
//		);

}
