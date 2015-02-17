package mv.model.mvsystem.out;

import java.io.FileWriter;
import java.io.IOException;

public class OutStreamFile implements OutStream {
	String fname;
	FileWriter f;
	
	public OutStreamFile(String fname) {
		this.fname = fname;
	}
	public void open() throws IOException {
		f = new FileWriter(fname);
	}
	public void close() throws IOException {
		f.close();
	}
	public void writeChar(int c) throws IOException {
		f.write((char)c);
	}
	
	public void reset() throws IOException {
		close();
		open();
	}

}
