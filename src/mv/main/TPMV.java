package mv.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import mv.ArgsParser;
import mv.command.CommandInterpreter;
import mv.command.CommandParser;
import mv.cpu.CPU;
import mv.cpu.ProgramMV;
import mv.exceptions.CPUParserException;
import mv.exceptions.cpuExceptions.MVError;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.gui.swing.MainWindow;
import mv.mvSystem.in.InStream;
import mv.mvSystem.in.InStreamFile;
import mv.mvSystem.in.InStreamNada;
import mv.mvSystem.in.InStreamStd;
import mv.mvSystem.out.OutStream;
import mv.mvSystem.out.OutStreamFile;
import mv.mvSystem.out.OutStreamNada;
import mv.mvSystem.out.OutStreamStd;

import org.apache.commons.cli.ParseException;

/** 
 * @author Samuel Lapuente y Héctor Laria. 2º A 
 */
public class TPMV {
	// si tuviéramos algún modo más, sería interesante hacer una clase properties para aunar todo
	private static final int _BATCH_MODE = 0;
	private static final int _INTER_MODE = 1;
	private static final int _WINDOW_MODE = 2;
	private static final int _NULL_STREAM = 3;
	private static final int _STD_STREAM = 4;
	private static final int _FILE_STREAM = 5;
	
	private static CPU cpu;
	private static int executionMode = _INTER_MODE;
	private static int inStreamMode = _NULL_STREAM;
	private static int outStreamMode = _NULL_STREAM;
	private static String inStreamFileName;
	private static String outStreamFileName;
	private static String programFileName;
	
	public static void main(String[] args) { startMV(args); }
	
	/** 
	 * Se encarga de la ejecución y la entrada/salida. 
	 * También muestra los errores que se puedan dar. 
	 * @param args Argumentos de entrada. 
	 */
	public static void startMV(String[] args) {
		cpu = new CPU();
		
		try {
			parseOptions(args);

			switch (executionMode) {
			case _INTER_MODE:
				interactiveMode(); break;
			case _BATCH_MODE:
				batchMode(); break;
			case _WINDOW_MODE:
				windowMode(); break;
			}
		} catch (ParseException | FileNotFoundException e) {
			System.err.println ("Uso incorrecto: " + e.getMessage()  + System.lineSeparator() +
					"Use -h|--help para más detalles.");
		} catch (NoInstructionsException e) {
			System.err.println("No hay instrucciones. Por favor inténtelo de nuevo.");
			
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		if (executionMode != _WINDOW_MODE)
			System.exit(1);
	}
	
	private static void parseOptions (String[] args) throws ParseException {
		ArgsParser parser = new ArgsParser();
		parser.parse(args);
		
		if (parser.helpAsked()) {
			parser.printHelp();
			System.exit(1);
		}
		
		configureParameters (parser);
	}
	
	private static void interactiveMode() throws NoInstructionsException, CPUParserException, IOException {
		Scanner sc = new Scanner(System.in);
		ProgramMV program;
		InStream in = createInStream();
		OutStream out = createOutStream();
		
		in.open();
		out.open();
		cpu.setInStream(in);
		cpu.setOutStream(out);
		
		if (programFileName == null) program = ProgramMV.readProgram(sc);
		else program = ProgramMV.readProgram (programFileName);
		
		cpu.loadProgram(program);
		System.out.println("El programa instroducido es:" + System.lineSeparator() + program);
		
		CommandInterpreter com;
		CommandInterpreter.configureCommandInterpreter(cpu, executionMode == _INTER_MODE);
		while (!cpu.isHALT() && !CommandInterpreter.isQuit()) {
			try {
				System.out.print("> ");
				com = CommandParser.parse (sc.nextLine());
				
				com.executeCommand();
			}
			catch (CPUParserException e) {
				System.err.println("Error: Comando incorrecto");
			}
			catch (MVError e) {
				System.err.println(e.getMessage());
			}

			if (!cpu.isHALT() && !CommandInterpreter.isQuit()) System.out.println(cpu);
		}
		
		sc.close();
		cpu.getInStream().close();
		cpu.getOutStream().close();
	
	}
	
	private static void batchMode() throws CPUParserException, IOException, NoInstructionsException {
		InStream in = createInStream();
		OutStream out = createOutStream();
		
		in.open();
		out.open();
		cpu.setInStream(in);
		cpu.setOutStream(out);
		
		ProgramMV program = ProgramMV.readProgram(programFileName);
		cpu.loadProgram(program);
		
		try {
			cpu.run();
		} catch (MVError e) {
			System.err.println(e.getMessage());
		}
		
		cpu.getInStream().close();
		cpu.getOutStream().close();
	}
	
	private static void windowMode() throws FileNotFoundException, IOException, CPUParserException {
		InStream in = createInStream();
		OutStream out = createOutStream();
		
		in.open();
		out.open();
		cpu.setInStream(in);
		cpu.setOutStream(out);
		
		ProgramMV program = ProgramMV.readProgram(programFileName);
		cpu.loadProgram(program);
		
		@SuppressWarnings("unused")
		MainWindow view = new MainWindow (cpu);
		
	}
	
	private static void configureParameters (ArgsParser parser) {
		executionMode = parser.getExecutionMode();
		inStreamMode = parser.getInStreamMode();
		outStreamMode = parser.getOutStreamMode();
		inStreamFileName = parser.getInStreamFileName();
		outStreamFileName = parser.getOutStreamFileName();
		programFileName = parser.getProgramFileName();
	}
	
	private static OutStream createOutStream() {
		OutStream out;
		switch (outStreamMode) {
		case _STD_STREAM: out = new OutStreamStd(); break;
		case _FILE_STREAM: out = new OutStreamFile (outStreamFileName); break;
		case _NULL_STREAM: out = new OutStreamNada(); break;
		default: out = null;
		}
		return out;
	}
	
	private static InStream createInStream() {
		InStream in;
		switch (inStreamMode) {
		case _STD_STREAM: in = new InStreamStd();break;
		case _FILE_STREAM: in = new InStreamFile (inStreamFileName);break;
		case _NULL_STREAM: in = new InStreamNada(); break;
		default: in = null;
		}
		return in;
	}
}