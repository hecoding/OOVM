package mv.model.ins.branch;

import mv.model.ins.Instruction;
/**
 * Clase de la cual heredan las instrucciones de salto condicional (BF BT RBF RBT)
 *  e incondicional (Jump y RJump)
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Branch implements Instruction {
	
	protected int argumento;
	protected abstract Instruction crear (int n);
	
	/**
	 * Clase que instancia una instrucción en caso de ser correcta.
	 */
	public Instruction parseOp (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 2){
			String thisCadena[] = this.toString().split(" ");
			if (cadena[0].equalsIgnoreCase(thisCadena[0])){
				return crear (Integer.parseInt(cadena[1]));
			}
			else return null;
		}
		else return null;
	}
}
