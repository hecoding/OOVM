package mv;

/**
 * Clase que parsea la instrucción introducida por el usuario.
 * @author Samuel Lapuente y Héctor Laria.
 *
 */
public class InstructionParser {
	
	private static boolean esNum(String s) {
		char[] numeroChar = new char[s.length()];
		boolean esNumero = true;
		int i = 0;
		
		numeroChar = s.toCharArray();	
		if (numeroChar[0] == '-' || numeroChar[0] == '+') i++;
		
		while (i < numeroChar.length && esNumero) {
			if (numeroChar[i] < '0' || numeroChar[i] > '9')
				esNumero = false;
			i++;
		}
		
		return esNumero;
	}

	/**
	 * Distingue si el string introducido es una instrucción válida.
	 * @param s String que contiene la supuesta instrucción.
	 * @return La instrucción, en caso de ser inválida, devuelve null.
	 */
	public static Instruction parse(String s) {
		String[] cadenaInstruccion = s.split(" ");
		Instruction i = null;

		if (cadenaInstruccion.length == 1) {
			if (Instruction.isNoArgInstruction(cadenaInstruccion[0]))
				i = new Instruction(InstructionEnum.valueOf(cadenaInstruccion[0].toUpperCase()));
		} 
		else if (cadenaInstruccion.length == 2) {
			if (Instruction.isArgInstruction(cadenaInstruccion[0]) && esNum(cadenaInstruccion[1]))
				i = new Instruction(InstructionEnum.valueOf(cadenaInstruccion[0].toUpperCase()), Integer.parseInt(cadenaInstruccion[1]));
		}
		
		return i;
	}
	
}