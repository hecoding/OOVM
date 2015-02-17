package mv.mvSystem.in;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InStreamFile implements InStream {
	String fname;
	FileReader f;
	Scanner s;
	
	public InStreamFile(String fname) {
		this.fname = fname;
	}
	
	public void open() throws FileNotFoundException, IOException {
		this.f = new FileReader(fname);
		this.s = new Scanner (f);
	}
	
	public void close() throws IOException {
		this.s.close();
		this.f.close();
	}
	
	public int read() throws IOException {
		if (f.ready()) return f.read();
		else return -1;
	}

	
}
