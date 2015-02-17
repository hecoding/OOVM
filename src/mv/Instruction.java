package mv;

/**
 * Clase para trabajar con las instrucciones del repertorio.
 * @author Samuel Lapuente y H�ctor Laria.
 *
 */
public class Instruction {
	private InstructionEnum instruction;
	private int arg = 0;
	
	/**
	 * Constructor de instruction sin argumento.
	 * @param inst Enumerado de instrucci�n.
	 */
	public Instruction (InstructionEnum inst) {
		this.instruction = inst;
	}
	
	/**
	 * Constructor de Instruction con argumento.
	 * @param inst Enumerado de instrucci�n.
	 * @param arg Argumento de la instrucci�n.
	 */
	public Instruction (InstructionEnum inst, int arg) {
		this.instruction = inst;
		this.arg = arg;		
	}
	
	/**
	 * Getter de instrucci�n.
	 * @return La instrucci�n.
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
	 * M�todo toString de instrucci�n.
	 */
	public String toString() {
		String s = this.instruction.toString();
		
		if (isArgInstruction(s))
			s += " " + this.arg;
			
		return  s;
	}
	
	/**
	 * M�todo para determinar si es una instrucci�n con argumento.
	 * @param s Instrucci�n a comprobar en String.
	 * @return Si es una instrucci�n v�lida del repertorio.
	 */
	public static boolean isArgInstruction (String s) {
		boolean es = false;
		
		if (s.equalsIgnoreCase("PUSH"))		  es = true;
		else if (s.equalsIgnoreCase("LOAD"))  es = true;
		else if (s.equalsIgnoreCase("STORE")) es = true;
		
		return es;
	}
	
	/**
	 * M�todo para determinar si es una instrucci�n unitaria.
	 * @param s Instrucci�n a comprobar en String.
	 * @return Si es una instrucci�n v�lida del repertorio.
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