package mv;

/**
 * Emula una pila virtual.
 * @author Samuel Lapuente y H�ctor Laria.
 *
 */
public class OperandStack {
	private int[] stack;
	private int capacity = 50;
	private int size;
	
	/**
	 * Constructor de OperandStack.
	 */
	public OperandStack () {
		this.stack = new int[this.capacity];
		this.size = 0;
	}
	
	private void resize() {
		int nuevaCapacity = (3 * this.capacity / 2) + 1;
		int[] nuevoStack = new int[nuevaCapacity];
		
		for(int i=0; i<this.capacity; i++) { // Implementar .clone no merece para un array.
			nuevoStack[i] = this.stack[i];
		}
		
		this.capacity = nuevaCapacity;
		this.stack = nuevoStack;
	}
	
	/**
	 * Getter del tama�o de la pila.
	 * @return Tama�o.
	 */
	public int getSize() {
		
		return this.size;
	}
	
	/**
	 * M�todo para comprobar si la pila est� vac�a.
	 * @return Vac�a.
	 */
	public boolean esVacia() {
		
		return this.size == 0;
	}
	
	/**
	 * Guarda una palabra en la pila utilizando el m�todo LIFO.
	 * @param m Palabra.
	 */
	public void apilar(int m) {
		if (this.size >= this.capacity)
			this.resize();
		
		this.stack[this.size] = m;
		this.size++;
	}
	
	/**
	 * Extrae una palabra de la cima de la pila.
	 * @return Cima de la pila.
	 */
	public int desapilar() {
		int m;
		
		m = this.stack[this.size - 1];
		if (this.size != 0) this.size--;
		
		return m;
	}
	
	/**
	 * M�todo toString de la pila.
	 */
	public String toString() {
		String s = "Pila de operandos: ";
		String palabras = "";
		
		for (int i = 0; i < this.size; i++) {
			palabras += this.stack[i] + " ";
		}
		
		if (palabras.equals(""))
			return s + "<vac�a>";
		else
			return s + palabras;
	}
}