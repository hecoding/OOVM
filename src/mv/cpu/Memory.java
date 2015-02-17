package mv.cpu;

import java.util.ArrayList;

/** 
 * Emula una memoria ilimitada. 
 * @author Samuel Lapuente y Héctor Laria. 
 * 
 */
public class Memory<T> {
	private T[] memory;
    private int capacity = 100;
    
    @SuppressWarnings("unchecked")
	public Memory() {
        this.memory = (T[]) new Integer[this.capacity];
          
        for (int i = 0; i < this.memory.length; i++) {
            this.memory[i] = null;
        }
    }
    
	public static class MemCell<T> {
	    int pos;
	    T value;
	    
	    public MemCell(int pos, T value) {
	    	this.pos = pos;
	    	this.value = value;
	    }
	    
	    public int getPos() {
	    	return pos;
	    }
	    
	    public T getValue() {
	    	return value;
	    }
    }
	
    public ArrayList<MemCell<T>> getArray() {
    	ArrayList<MemCell<T>> array = new ArrayList<MemCell<T>>();
    	for (int i = 0; i < this.capacity; i++) {
    		if (this.memory[i] != null) {
    			array.add(new MemCell<T> (i, memory[i]));
    		}
    			
    	}
    	return array;
    }
  
    @SuppressWarnings("unchecked")
	private void resize(int pos) {
        int nuevaCapacity = (3 * pos / 2) + 1;
        Integer[] nuevaMem = new Integer[nuevaCapacity];
          
        for(int i = 0; i < this.capacity; i++) { // Implementar .clone no merece para un solo array.
            nuevaMem[i] = (Integer) this.memory[i];
        }
          
        this.capacity = nuevaCapacity;
        this.memory = (T[]) nuevaMem;
    }
  
    public void guardar (int pos, T dato) {
    	if (pos < 0 ) throw new NullPointerException("La posición del elemento a guardar debe ser igual a 0 o positiva");
    	if (pos >= this.capacity) this.resize(pos);
        this.memory[pos] = dato;
    }
  
    public int cargar (int pos) {
    	if (pos < 0) throw new NullPointerException("La posición del elemento a cargar debe ser igual a 0 o positiva");
        if (pos >= this.capacity) return (int) (Math.random() * 100);
        else {
            if (this.memory[pos] == null)
                return (int) (Math.random() * 100);
            else
                return (Integer) this.memory[pos];
        }
    }
  
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

	public int getCapacity() {
		return this.capacity;
	}
} 
