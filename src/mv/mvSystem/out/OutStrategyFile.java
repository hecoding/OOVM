package mv.mvSystem.out;

import java.io.FileWriter;
import java.io.IOException;

public class OutStrategyFile implements OutStrategy {
	String fname;
	FileWriter f;
	
	public OutStrategyFile(String fname) {
		this.fname = fname;
	}
	public void open() throws IOException {
		f = new FileWriter(fname);
	}
	public void close() throws IOException {
		f.close();
	}
	public void write(int c) throws IOException {
		f.write((char)c);
	}

}
