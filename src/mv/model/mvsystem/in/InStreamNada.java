package mv.model.mvsystem.in;

import java.io.IOException;

public class InStreamNada implements InStream {
	
	public void open() { }
	public void close() { }
	public int read() { return -1; }
	public void reset() throws IOException { }

}
