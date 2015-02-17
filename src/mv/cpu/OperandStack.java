package mv.cpu;

import java.util.Stack;

/** 
 * Emula una pila virtual. 
 * @author Samuel Lapuente y Héctor Laria. 
 * @param <T>
 * 
 */
@SuppressWarnings("serial")
public class OperandStack<T> extends Stack<T> {

	public String toString() {
		String s = "Pila de operandos: ";
		String palabras = "";
		
		if (this.isEmpty()) return s + "<vacía>";
		else {
			for (T i : this)
				palabras += i + " ";
			
			return s + palabras;
		}
	}
}