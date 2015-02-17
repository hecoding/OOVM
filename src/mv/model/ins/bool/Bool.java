package mv.model.ins.bool;

import mv.model.ins.Instruction;
/**
 * Clase de la cual las instrucciones booleanas de and, not y or
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Bool implements Instruction {
	
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
