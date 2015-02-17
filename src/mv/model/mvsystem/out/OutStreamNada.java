package mv.model.mvsystem.out;

import java.io.IOException;

public class OutStreamNada implements OutStream {

	public void open() { }
	public void close() { }
	public void writeChar(int c) {}
	public void reset() throws IOException { }

}
