package org.sensor.foundation.mapper;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.sensor.foundation.model.SensorMeasurement;
import org.sensor.foundation.values.SensorValue;
import org.sensor.foundation.values.SensorValueType;

public class SensorMeasurementMapper {
	public String mapToJson(SensorMeasurement measurement) {
		DateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		String message = measurement.getSensorId() 
							+ ";" 
							+ format.format(measurement.getMeasurementDate())
							+ ";"
							+ measurement.getMeasurementValue().getDefinition().getName()
							+ ";"
							+ measurement.getMeasurementValue().getValue();
		return message;
	}
	
	public SensorMeasurement mapToObject(String jsonRepresentation) {
		return new SensorMeasurement("TODO", null, null);

	}
	
	public static void main(String[] args) throws JsonParseException, IOException {
		JsonFactory factory = new MappingJsonFactory();
		
//		Map map = (parser.readValueAs(Map.class));
//		System.out.println(map.get("hello"));
//		System.out.println(map.get("test"));

		SensorMeasurement measurement = 
				new SensorMeasurement(
						"hello", 
						new Date(), 
						SensorValueType.DECIMAL.definition("temperature").withValue(new BigDecimal("2.0")));
		StringWriter writer = new StringWriter();
		
		factory.createJsonGenerator(writer).useDefaultPrettyPrinter().writeObject(measurement);
//		factory.
		
//		ObjectMapper mapper = new ObjectMapper();
//		SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null));
//		testModule.addSerializer(new JsonSerializer<SensorValue>(){
//
//			@Override
//			public void serialize(SensorValue value, JsonGenerator jgen,
//					SerializerProvider provider) throws IOException,
//					JsonProcessingException {
//				// TODO Auto-generated method stub
//				
//			}});
		
		//testModule.addDeserializer(type, deser)
		//mapper.registerModule(module);  
		
		System.out.println(writer.toString());
		
		JsonParser parser = factory.createJsonParser(writer.toString());
		Map newM = parser.readValueAs(Map.class);
		System.out.println(newM);
	}

}
