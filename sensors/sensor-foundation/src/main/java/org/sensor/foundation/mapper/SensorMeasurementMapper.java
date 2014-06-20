package org.sensor.foundation.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.sensor.foundation.model.SensorMeasurement;

public class SensorMeasurementMapper {
	public String mapToJson(SensorMeasurement measurement) {
		DateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		String message = measurement.getId() 
							+ ";" 
							+ format.format(measurement.getDate())
							+ ";"
							+ measurement.getType()
							+ ";"
							+ measurement.getValue().getValue();
		return message;
	}
	
	public SensorMeasurement mapToObject(String jsonRepresentation) {
		return null;

	}
	
//	public static void main(String[] args) throws JsonParseException, IOException {
//		JsonFactory factory = new MappingJsonFactory();
//		
////		Map map = (parser.readValueAs(Map.class));
////		System.out.println(map.get("hello"));
////		System.out.println(map.get("test"));
//
//		SensorMeasurement measurement = 
//				new SensorMeasurement(
//						"hello", 
//						"mytype", 
//						new Date(), 
//						SensorValueType.DECIMAL.definition("temperature").withValue(new BigDecimal("2.0")));
//		StringWriter writer = new StringWriter();
//		
//		factory.createJsonGenerator(writer).useDefaultPrettyPrinter().writeObject(measurement);
////		factory.
//		
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
//		
//		//testModule.addDeserializer(type, deser)
//		//mapper.registerModule(module);  
//		
//		System.out.println(writer.toString());
//		
//		JsonParser parser = factory.createJsonParser(writer.toString());
//		SensorMeasurement newM = parser.readValueAs(SensorMeasurement.class);
//		System.out.println(newM);
//	}

}
