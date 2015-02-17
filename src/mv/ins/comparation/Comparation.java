package mv.ins.comparation;

import mv.ins.Instruction;

/**
 * Clase de la cual heredan las instrucciones de comparación (EQ EG GT LE LT)
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Comparation implements Instruction {
	
	protected abstract Instruction crear();
	
	/**
	 * Clase que instancia una instrucción en caso de ser correcta.
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
