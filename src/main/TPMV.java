package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import mv.controler.BatchControler;
import mv.controler.InteractiveControler;
import mv.controler.SwingControler;
import mv.model.cpu.CPU;
import mv.model.cpu.ProgramMV;
import mv.model.exceptions.CPUParserException;
import mv.model.exceptions.cpuExceptions.NoInstructionsException;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.in.InStreamFile;
import mv.model.mvsystem.in.InStreamNada;
import mv.model.mvsystem.in.InStreamStd;
import mv.model.mvsystem.out.OutStream;
import mv.model.mvsystem.out.OutStreamFile;
import mv.model.mvsystem.out.OutStreamNada;
import mv.model.mvsystem.out.OutStreamStd;
import mv.view.console.BatchView;
import mv.view.console.InteractiveView;
import mv.view.gui.swing.MainWindow;

import org.apache.commons.cli.ParseException;

import utils.ArgsParser;

/** 
 * @author Samuel Lapuente y Héctor Laria. 2º A IS
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
		
		InteractiveControler ctrl = new InteractiveControler(cpu);
		@SuppressWarnings("unused")
		InteractiveView view = new InteractiveView(cpu);
		
		ctrl.start();
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
		
		BatchControler ctrl = new BatchControler(cpu);
		@SuppressWarnings("unused")
		BatchView view = new BatchView(cpu);
		
		ctrl.start();
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
		
		SwingControler ctrl = new SwingControler(cpu);
		@SuppressWarnings("unused")
		MainWindow view = new MainWindow(ctrl, cpu, cpu.getOperandStack(), cpu.getMemory());
		
		ctrl.start();
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