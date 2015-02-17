package mv.mvSystem.in;

import java.io.IOException;
import java.util.Scanner;

public class InStreamStd implements InStream {
	Scanner s;
	
	public void open() { this.s = new Scanner (System.in); }
	public void close() { s.close(); }
	public int read() throws IOException {
		int c = System.in.read();
		while ( System.in.available() > 0 ) System.in.read();
		
		return c;
	}
	
}
