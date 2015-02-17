package ins.arithmetics;

import ins.Instruction;

/**
 * Clase de las que heredan las instrucciones de suma, divisi�n, multiplicaci�n y resta
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
public abstract class Arithmetics implements Instruction {
	
	protected abstract Instruction crear();
	
	/**
	 * Clase que instancia una instrucci�n en caso de ser correcta.
	 */
	public Instruction parseOp (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 1){
			if (cadena[0].equalsIgnoreCase(this.toString())){
				return crear();
			}
			else return null;
		}
		else return null;
	}
	
}
