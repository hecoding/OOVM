package mv.cpu;

import java.util.Stack;

/** 
 * Emula una pila virtual. 
 * @author Samuel Lapuente y H�ctor Laria. 
 * 
 */
public class OperandStack {
	private Stack<Integer> stack;

	public OperandStack () {
		this.stack = new Stack<Integer>();
	}

	public int getSize() {
		
		return this.stack.size();
	}

	public boolean esVacia() {
		
		return this.stack.isEmpty();
	}
	
	public void apilar(int m) {
		this.stack.push(m);
	}

	public int desapilar() {
		
		return this.stack.pop(); // throws NoArgsException
	}

	public String toString() {
		String s = "Pila de operandos: ";
		String palabras = "";
		
		if (this.stack.isEmpty()) return s + "<vac�a>";
		else {
			for (Integer i : this.stack)
				palabras += i + " ";
			
			return s + palabras;
		}
	}
}