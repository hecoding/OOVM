package mv;

/**
 * Emula la cpu de la máquina virtual, conteniendo la memoria y la pila.
 * Así como las implementaciones de las instrucciones del repertorio.
 * @author Samuel Lapuente y Héctor Laria.
 */
public class CPU {
	
	private Memory memory;
	private OperandStack stack;
	private boolean shutdown;
	
	/**
	 * Constructor de CPU.
	 */
	public CPU() {
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.shutdown = false;
	}
	
	/**
	 * Ejecuta la instrucción del repertorio conocido.
	 * @param instr Instrucción a ejecutar.
	 * @return Si la ejecución ha sido correcta o no.
	 */
	public boolean execute (Instruction instr) {
		boolean ok = true;
		
		if (instr.getInstruction() != null) // Aunque normalmente no llega null.
			
			switch (instr.getInstruction()) {
				case HALT: this.halt(); break;
				case OUT: if (this.stack.esVacia()) ok = false;
						  else System.out.println(this.out()); break;
				case LOAD: ok = this.load(instr.getArgument()); break;
				case STORE: ok = this.store(instr.getArgument()); break;
				case PUSH: this.push(instr.getArgument()); break;
				case POP: ok = this.pop(); break;
				case FLIP: ok = this.flip(); break;
				case ADD: ok = this.add(); break;
				case SUB: ok = this.sub(); break;
				case MUL: ok = this.mul(); break;
				case DIV: ok = this.div(); break;
				case DUP: ok = this.dup(); break;
		}
		
		return ok;
	}
	
	private void halt() {
		this.shutdown = true;
	}
	
	private char out() { // las comprobaciones las hace en el switch porque devuelve char
		int cima = this.stack.desapilar();

		return (char) (cima);
	}
	
	private boolean load(int pos) {
		if (pos < 0) return false;
		else {
			this.stack.apilar(this.memory.cargar(pos));
			return true;
		}
	}
	
	private boolean store(int pos) {
		if (this.stack.esVacia() || pos < 0) return false;
		else {
			this.memory.guardar(pos, this.stack.desapilar());
			return true;
		}
	}
	
	private void push (int m) {
		this.stack.apilar(m);
	}
	
	private boolean pop() {
		if(this.stack.esVacia()) return false;
		else {
			this.stack.desapilar();
			return true;
		}
	}
	
	private boolean flip() {
		if (this.stack.getSize() < 2) return false;
		else {
			int temp1 = this.stack.desapilar();
			int temp2 = this.stack.desapilar();
			this.stack.apilar(temp1);
			this.stack.apilar(temp2);
			
			return true;
		}
	}
	
	private boolean add() {
		if (this.stack.getSize() < 2) return false;
		else {
			int cima = this.stack.desapilar();
			int subcima = this.stack.desapilar();
			
			this.stack.apilar(subcima + cima);
			
			return true;
		}
	}
	
	private boolean sub() {
		if (this.stack.getSize() < 2) return false;
		else {
			int cima = this.stack.desapilar();
			int subcima = this.stack.desapilar();
			
			this.stack.apilar(subcima - cima);
			
			return true;
		}
	}
	
	private boolean mul() {
		if (this.stack.getSize() < 2) return false;
		else {
			int cima = this.stack.desapilar();
			int subcima = this.stack.desapilar();
			
			this.stack.apilar(subcima * cima);
			
			return true;
		}
	}
	
	private boolean div() {
		if (this.stack.getSize() < 2) return false;
		else {
			int cima = this.stack.desapilar();
			
			if (cima == 0) {
				this.stack.apilar(cima); // De no hacer esto, nos quedamos sin cima y la división tampoco se hace.
										
				return false;
			}
			else {
				int subcima = this.stack.desapilar();

				this.stack.apilar(subcima / cima);
				return true;
			}
		}
	}
	
	private boolean dup() {
		if (this.stack.esVacia()) return false;
		else {
			int cima = this.stack.desapilar();

			this.stack.apilar(cima);
			this.stack.apilar(cima);
			
			return true;
		}
	}	
	
	/**
	 * Getter del atributo privado Halt utilizado para apagar la maquina virtual.
	 * @return Apagar.
	 */
	public boolean getHALT () {
		
		return this.shutdown;
	}
	
	/**
	 * Método toString de CPU.
	 */
	public String toString() {
		
		return this.memory.toString() + System.lineSeparator() + this.stack.toString();
	}
	
}
