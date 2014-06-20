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

import org.b.v.hub.SensorHubEvents;
import org.b.v.values.SensorValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SensorController {
	
//	@Autowired
//	private org.b.v.system.SensorHostSystem system;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private SensorHubEvents events;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorController.class);
	
	
	public SensorController() throws IOException{
	}

	@RequestMapping(value = "/sensors", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<String> sensorsList() throws IOException, InterruptedException {
		LOGGER.info("List of sensors");
		List<String> list = new ArrayList<String>();
		list.add("hello");
		return list;

	}

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
	
	@RequestMapping(value = "/sensors/{id}/errors", method = RequestMethod.GET)
	@ResponseBody
	public List<String> errors(@PathVariable String id) throws IOException, InterruptedException {
		LOGGER.info("Sensor values");
		return new ArrayList<String>();
	}

	@RequestMapping(value = "/sensors/{sensorId}/configurations", method = RequestMethod.PUT)
	@ResponseBody
	public void applyNewConfiguration(@PathVariable String sensorId,@RequestBody List<SensorValue> configurations) throws IOException, InterruptedException {
		//store
		//apply id
		//and status
		//events.newConfiguration();
	}

	@RequestMapping(value = "/sensors/{sensorId}/configurations/{configurationId}", method = RequestMethod.GET)
	@ResponseBody
	public List<SensorValue> configurationDetail(@PathVariable String sensorId) throws IOException, InterruptedException {
		return new ArrayList<SensorValue>();
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
