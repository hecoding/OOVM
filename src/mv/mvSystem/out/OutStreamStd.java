package mv.mvSystem.out;

public class OutStreamStd implements OutStream {

	public void open() { }
	public void close() { }
	public void writeChar(int c) {
		System.out.print( (char)c );
	}

}
