package mv.cpu;

import java.util.Stack;

/** 
 * Emula una pila virtual. 
 * @author Samuel Lapuente y H�ctor Laria. 
 * @param <T>
 * 
 */
@SuppressWarnings("serial")
public class OperandStack<T> extends Stack<T> {

	public String toString() {
		String s = "Pila de operandos: ";
		String palabras = "";
		
		if (this.isEmpty()) return s + "<vac�a>";
		else {
			for (T i : this)
				palabras += i + " ";
			
			return s + palabras;
		}
	}
}