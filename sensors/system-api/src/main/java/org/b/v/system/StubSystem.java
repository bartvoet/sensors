package org.b.v.system;

import java.io.IOException;

//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("system")
//@Primary
//@Profile("stubbed")
public class StubSystem implements SensorHostSystem {

	public I2CConnection createI2CConnection(int adress) throws IOException {
		return new I2CConnection() {
			@Override
			public void write(byte b) throws IOException {
				
			}
			
			public int read(byte[] buffer, int offset, int size) throws IOException {
				byte[] bytes = new byte[3];
				bytes[0]=99;
				bytes[1]=3;
				bytes[2]=3;
				return 3;
			}


			@Override
			public void write(byte... b) throws IOException {
				
			}
		};
	}

	public void waitMillis(int milliseconds) {
		//do nothing since this is only used for simulation
	}

}
