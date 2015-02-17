package mv.ins;

import mv.exceptions.CPUParserException;
import mv.ins.arithmetics.Add;
import mv.ins.arithmetics.Div;
import mv.ins.arithmetics.Mul;
import mv.ins.arithmetics.Sub;
import mv.ins.bool.And;
import mv.ins.bool.Not;
import mv.ins.bool.Or;
import mv.ins.branch.BF;
import mv.ins.branch.BT;
import mv.ins.branch.Jump;
import mv.ins.branch.JumpInd;
import mv.ins.branch.RBF;
import mv.ins.branch.RBT;
import mv.ins.branch.RJump;
import mv.ins.comparation.EQ;
import mv.ins.comparation.GT;
import mv.ins.comparation.LE;
import mv.ins.comparation.LT;
import mv.ins.sequential.Dup;
import mv.ins.sequential.Flip;
import mv.ins.sequential.Halt;
import mv.ins.sequential.In;
import mv.ins.sequential.Load;
import mv.ins.sequential.LoadInd;
import mv.ins.sequential.Out;
import mv.ins.sequential.Pop;
import mv.ins.sequential.Push;
import mv.ins.sequential.Store;
import mv.ins.sequential.StoreInd;

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
		new In(),
		new Jump(),
		new JumpInd(),
		new LE(),
		new Load(),
		new LoadInd(),
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
		new StoreInd(),
		new Sub()
	};
	
	/**
	 * Método que instancia una instrucción en caso de ser correcta. 
	 * Esta clase llama a los parsers de las instrucciones individuales.
	 * @param s String a parsear.
	 * @return Una instrucción si se puede parsear la entrada, null en el caso contrario.
	 * @throws CPUParserException Si el string no contiene una instrucción parseable
	 */
	public static Instruction parse (String s) throws CPUParserException {
		try {
			String[] cadenaIns = s.split(";");
			if (cadenaIns.length == 0) return null;
			if (cadenaIns[0].equals ("")) return null;
			
			for(Instruction ins : instrucciones){
				Instruction instruccion = ins.parseOp(cadenaIns[0]);
				
				if (instruccion != null)
					return instruccion;
			}
		} catch (NumberFormatException e) {
			throw new CPUParserException ("los argumentos deben ser números");
		}
		
		throw new CPUParserException();
	}
}