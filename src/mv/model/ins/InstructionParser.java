package mv.model.ins;

import mv.model.exceptions.CPUParserException;
import mv.model.ins.arithmetics.Add;
import mv.model.ins.arithmetics.Div;
import mv.model.ins.arithmetics.Mul;
import mv.model.ins.arithmetics.Sub;
import mv.model.ins.bool.And;
import mv.model.ins.bool.Not;
import mv.model.ins.bool.Or;
import mv.model.ins.branch.BF;
import mv.model.ins.branch.BT;
import mv.model.ins.branch.Jump;
import mv.model.ins.branch.JumpInd;
import mv.model.ins.branch.RBF;
import mv.model.ins.branch.RBT;
import mv.model.ins.branch.RJump;
import mv.model.ins.comparation.EQ;
import mv.model.ins.comparation.GT;
import mv.model.ins.comparation.LE;
import mv.model.ins.comparation.LT;
import mv.model.ins.sequential.Dup;
import mv.model.ins.sequential.Flip;
import mv.model.ins.sequential.Halt;
import mv.model.ins.sequential.In;
import mv.model.ins.sequential.Load;
import mv.model.ins.sequential.LoadInd;
import mv.model.ins.sequential.Out;
import mv.model.ins.sequential.Pop;
import mv.model.ins.sequential.Push;
import mv.model.ins.sequential.Store;
import mv.model.ins.sequential.StoreInd;

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