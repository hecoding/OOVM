package mv.controler;

import java.io.IOException;
import java.util.Scanner;

import mv.model.command.CommandInterpreter;
import mv.model.command.CommandParser;
import mv.model.cpu.CPU;
import mv.model.exceptions.CPUParserException;
import mv.model.exceptions.cpuExceptions.MVError;
import mv.model.exceptions.cpuExceptions.NoInstructionsException;

public class InteractiveControler extends Controler {
	
	public InteractiveControler(CPU cpu) {
		super(cpu);
	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		CommandInterpreter com;
		CommandInterpreter.configureCommandInterpreter(cpu);

		System.out.println("El programa instroducido es:" + System.lineSeparator() + cpu.getProgram());
		
		while (!cpu.isHALT() && !CommandInterpreter.isQuit()) {
			try {
				System.out.print("> ");
				com = CommandParser.parse (sc.nextLine());
				
				com.executeCommand();
			}
			catch (CPUParserException e) {
				System.err.println("Error: Comando incorrecto");
			}
			catch (MVError e) {}
			catch (NoInstructionsException e) {}
		}
		
		sc.close();
		try {
			getInStream().close();
			getOutStream().close();
		} catch (IOException e) {}
	}
	
}
