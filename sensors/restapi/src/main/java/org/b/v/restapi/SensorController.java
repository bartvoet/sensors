package org.b.v.restapi;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.b.v.sensors.api.Sensor;
import org.b.v.sensors.sensirion.SHT21OverI2C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SensorController {
	
	@Autowired
	private org.b.v.system.System system;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorController.class);
	
	
	public SensorController() throws IOException{
	}

//	private List<Sensor> sensors() throws IOException {
//		List<Sensor> sensors = new ArrayList<Sensor>();
//		sensors.add(new SHT21OverI2C(system.createI2CConnection(0x40)));
//		return sensors;
//	}
	
	@RequestMapping(value = "/sensors", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<String> sensorsList() throws IOException, InterruptedException {
		LOGGER.info("List of sensors");
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		return list;

	}

//	@RequestMapping(value = "/sensors/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String,String> sensorValues(@PathVariable Integer id) throws IOException, InterruptedException {
//		LOGGER.info("Sensor values");
//		Map<String,String> map = new HashMap<String,String>();
//		for(Sensor sensor : sensors()){
//			for(String type : sensor.type().getTypeNames()) {
//				map.put(type, Double.toString(sensor.meassure(type)));
//			}
//		}
//		return map;
//		
//	}

	@RequestMapping(value = "/sensors/{id}/measurements", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> sensorValuesOverview(@PathVariable String id) throws IOException, InterruptedException {
		LOGGER.info("Sensor values");
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("sensorId", id);
		final DateFormat format = new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss");
		return jdbcTemplate.query("select * from SensorMeassurement where sensorId=:sensorId", 
				parameters, 
				new RowMapper<Map<String,Object>>(){
					@Override
					public Map<String,Object> mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Map<String,Object> result = new HashMap<String,Object>();
						result.put("measurementTime", format.format(rs.getTimestamp("measurementTime")));
						result.put("functionality", rs.getString("functionality"));
						result.put("measurementValue", rs.getDouble("measurementValue"));
						return result;
					}
				}
			);
	}
	
//	create table SensorMeassurement (
//			sensorId varchar(50),
//			measurementTime time,
//			functionality varchar(50),
//			measurementValue double
//		);


	
//	@RequestMapping(value = "/teams/{teamId}", method = RequestMethod.GET)
//	@ResponseBody
//	public Team teamDetails(@PathVariable Integer teamId) {
//		LOGGER.info("Team-data");
//		return 
//		template.queryForObject("select * from teams where teamid=?", TEAM_MAPPER,teamId);
//	}
	
}
