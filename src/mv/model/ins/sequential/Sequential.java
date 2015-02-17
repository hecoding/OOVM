package mv.model.ins.sequential;

import mv.model.ins.Instruction;
/**
 * Clase de la que heredan las instrucciones secuenciales (Dup, Flip, Halt, Load, Out, Pop, Push y Store)
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Sequential implements Instruction {
	protected abstract Instruction crear();
	protected abstract Instruction crear (int n);
	
	/**
	 * Clase que instancia una instrucción en caso de ser correcta.
	 */
	public Instruction parseOp (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 2) {
			String thisCadena[] = this.toString().split(" ");
			if (cadena[0].equalsIgnoreCase(thisCadena[0]))
				return crear (Integer.parseInt(cadena[1]));
		}
		else if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase(this.toString()))
				return crear();
		}
		
		return null;
	}
}
