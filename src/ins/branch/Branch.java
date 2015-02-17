package ins.branch;

import ins.Instruction;
/**
 * Clase de la cual heredan las instrucciones de salto condicional (BF BT RBF RBT)
 *  e incondicional (Jump y RJump)
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
public abstract class Branch implements Instruction {
	
	protected int argumento;
	protected abstract Instruction crear (int n);
	
	/**
	 * Clase que instancia una instrucci�n en caso de ser correcta.
	 */
	public Instruction parseOp (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 2){
			String thisCadena[] = this.toString().split(" ");
			if (cadena[0].equalsIgnoreCase(thisCadena[0]) && esNum(cadena[1])){
				return crear (Integer.parseInt(cadena[1]));
			}
			else return null;
		}
		else return null;
	}
	
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
}