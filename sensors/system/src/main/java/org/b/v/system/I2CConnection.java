package org.b.v.system;

import java.io.IOException;

public interface I2CConnection {
	
	void write(byte b) throws IOException;

	int read(byte[] buffer, int offset, int size) throws IOException;


}
