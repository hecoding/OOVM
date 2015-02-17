package mv.model.mvsystem.out;

import java.io.IOException;

public class OutStreamStd implements OutStream {

	public void open() { }
	public void close() { }
	public void writeChar(int c) {
		System.out.print( (char)c );
	}
	
	public void reset() throws IOException { }

}
