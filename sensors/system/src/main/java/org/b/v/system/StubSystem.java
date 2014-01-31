package org.b.v.system;

import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile("stubbed")
public class StubSystem implements System {

	public I2CConnection createI2CConnection(int adress) throws IOException {
		return new I2CConnection() {
			public void write(byte b) throws IOException {
				
			}
			
			public int read(byte[] buffer, int offset, int size) throws IOException {
				byte[] bytes = new byte[3];
				bytes[0]=99;
				bytes[1]=3;
				bytes[2]=3;
				return 3;
			}
		};
	}

	public void wait(int milliseconds) {
		
	}

}
