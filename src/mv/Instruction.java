package mv;

/**
 * Clase para trabajar con las instrucciones del repertorio.
 * @author Samuel Lapuente y Héctor Laria.
 *
 */
public class Instruction {
	private InstructionEnum instruction;
	private int arg = 0;
	
	/**
	 * Constructor de instruction sin argumento.
	 * @param inst Enumerado de instrucción.
	 */
	public Instruction (InstructionEnum inst) {
		this.instruction = inst;
	}
	
	/**
	 * Constructor de Instruction con argumento.
	 * @param inst Enumerado de instrucción.
	 * @param arg Argumento de la instrucción.
	 */
	public Instruction (InstructionEnum inst, int arg) {
		this.instruction = inst;
		this.arg = arg;		
	}
	
	/**
	 * Getter de instrucción.
	 * @return La instrucción.
	 */
	public InstructionEnum getInstruction() {
		return this.instruction;
	}
	
	/**
	 * Getter de argumento.
	 * @return El argumento.
	 */
	public int getArgument() {
		return this.arg;
	}
	
	/**
	 * Método toString de instrucción.
	 */
	public String toString() {
		String s = this.instruction.toString();
		
		if (isArgInstruction(s))
			s += " " + this.arg;
			
		return  s;
	}
	
	/**
	 * Método para determinar si es una instrucción con argumento.
	 * @param s Instrucción a comprobar en String.
	 * @return Si es una instrucción válida del repertorio.
	 */
	public static boolean isArgInstruction (String s) {
		boolean es = false;
		
		if (s.equalsIgnoreCase("PUSH"))		  es = true;
		else if (s.equalsIgnoreCase("LOAD"))  es = true;
		else if (s.equalsIgnoreCase("STORE")) es = true;
		
		return es;
	}
	
	/**
	 * Método para determinar si es una instrucción unitaria.
	 * @param s Instrucción a comprobar en String.
	 * @return Si es una instrucción válida del repertorio.
	 */
	public static boolean isNoArgInstruction(String s) { 
		boolean es = false;

		if (s.equalsIgnoreCase("DUP")) 		 es = true;
		else if (s.equalsIgnoreCase("FLIP")) es = true;
		else if (s.equalsIgnoreCase("POP"))  es = true;
		else if (s.equalsIgnoreCase("ADD"))  es = true;
		else if (s.equalsIgnoreCase("SUB"))  es = true;
		else if (s.equalsIgnoreCase("MUL"))  es = true;
		else if (s.equalsIgnoreCase("DIV"))  es = true;
		else if (s.equalsIgnoreCase("OUT"))  es = true;
		else if (s.equalsIgnoreCase("HALT")) es = true;		
		
		return es;
	}
	
}