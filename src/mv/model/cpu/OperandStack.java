package mv.model.cpu;

import java.util.ArrayList;
import java.util.Stack;

import mv.model.observer.Observable;
import mv.model.observer.StackObserver;

/** 
 * Emula una pila virtual. 
 * @author Samuel Lapuente y Héctor Laria. 
 * @param <T>
 * 
 */
@SuppressWarnings("serial")
public class OperandStack<T> extends Stack<T> implements Observable<StackObserver<T>> {
	private ArrayList<StackObserver<T>> observers;
	
	public OperandStack() {
		super();
		
		this.observers = new ArrayList<StackObserver<T>>();
	}
	
	public T push (T v) {
		this.addElement(v);
		this.notifyPush(v);
		
		return v;
	}
	
	public T pop() {
		T v = this.lastElement();
		
		this.remove(this.size() - 1);
		this.notifyPop(v);
		
		return v;
	}
	
	public void reset() {
		this.clear();
		
		this.notifyReset();
	}
	
	public void addObserver(StackObserver<T> o) {
		this.observers.add(o);		
	}

	public void removeObserver(StackObserver<T> o) {
		this.observers.remove(o);
	}
	
	private void notifyPush(T v) {
		for (StackObserver<T> obs : this.observers)
			obs.onPush(v);
	}
	
	private void notifyPop(T v) {
		for (StackObserver<T> obs : this.observers)
			obs.onPop(v);
	}
	
	private void notifyReset() {
		for (StackObserver<T> obs : this.observers)
			obs.onStackReset();
	}

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