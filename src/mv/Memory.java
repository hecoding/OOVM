package mv;

/**
 * Emula una memoria ilimitada.
 * @author Samuel Lapuente y Héctor Laria.
 *
 */
public class Memory {
	private Integer[] memory;
	private int capacity = 100;
	
	/**
	 * Constructor de Memory.
	 */
	public Memory() {
		this.memory = new Integer[this.capacity];
		
		for (int i = 0; i < this.memory.length; i++) {
			this.memory[i] = null;
		}
	}

	private void resize(int pos) {
		int nuevaCapacity = (3 * pos / 2) + 1;
		Integer[] nuevaMem = new Integer[nuevaCapacity];
		
		for(int i=0; i<this.capacity; i++) { // Implementar .clone no merece para un solo array.
			nuevaMem[i] = this.memory[i];
		}
		
		this.capacity = nuevaCapacity;
		this.memory = nuevaMem;
	}
	
	/**
	 * Método que guarda un dato de pila en memoria.
	 * @param pos Posición donde guardar.
	 * @param dato Dato a guardar.
	 */
	public void guardar (int pos, int dato) {
		
		if (pos >=this.capacity) this.resize(pos);
		this.memory[pos] = dato;
	}
	
	/**
	 * Método que carga un dato de memoria a pila.
	 * @param pos Posición a cargar.
	 * @return Dato alojado en la posición.
	 */
	public int cargar (int pos) {
		if (pos >= this.capacity) return (int) (Math.random()*100);
		else {
			if (this.memory[pos] == null)
				return (int) (Math.random()*100);
			else
				return this.memory[pos];
		}
	}
	
	/**
	 * Método toString de Memory.
	 */
	public String toString() {
		String s = "Memoria: ";
		String celdas = "";
		
		for (int i = 0; i < this.capacity; i++) {
			if (this.memory[i] != null)
				celdas += "[" + i + "]:" + this.memory[i] + " ";
		}
		
		if (celdas.equals(""))
			return s + " <vacía>";
		else
			return s + celdas;
	}
}
