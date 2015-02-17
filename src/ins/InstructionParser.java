package ins;

import ins.arithmetics.Add;
import ins.arithmetics.Div;
import ins.arithmetics.Mul;
import ins.arithmetics.Sub;
import ins.bool.And;
import ins.bool.Not;
import ins.bool.Or;
import ins.branch.BF;
import ins.branch.BT;
import ins.branch.Jump;
import ins.branch.RBF;
import ins.branch.RBT;
import ins.branch.RJump;
import ins.comparation.EQ;
import ins.comparation.GT;
import ins.comparation.LE;
import ins.comparation.LT;
import ins.sequential.Dup;
import ins.sequential.Flip;
import ins.sequential.Halt;
import ins.sequential.Load;
import ins.sequential.Out;
import ins.sequential.Pop;
import ins.sequential.Push;
import ins.sequential.Store;

/**
 * Clase que parsea las instrucciones.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class InstructionParser {
	private static Instruction[] instrucciones = {
		new Add(),
		new And(),
		new BF(),
		new BT(),
		new Div(),
		new Dup(),
		new EQ(),
		new Flip(),
		new GT(),
		new Halt(),
		new Jump(),
		new LE(),
		new Load(),
		new LT(),
		new Mul(),
		new Not(),
		new Or(),
		new Out(),
		new Pop(),
		new Push(),
		new RBF(),
		new RBT(),
		new RJump(),
		new Store(),
		new Sub()
	};
	
	/**
	 * Clase que instancia una instrucción en caso de ser correcta. 
	 * Esta clase llama a los otros parse.
	 */
	public static Instruction parse (String s) {
		for(Instruction ins : instrucciones){
			Instruction instruccion = ins.parseOp(s);
			
			if (instruccion != null)
				return instruccion;
		}
		
		return null;
	}
}