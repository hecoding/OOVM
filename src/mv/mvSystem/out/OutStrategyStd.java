package mv.mvSystem.out;

public class OutStrategyStd implements OutStrategy {

	public void open() { }
	public void close() { }
	public void write(int c) {
		System.out.print( (char)c );
	}

}
