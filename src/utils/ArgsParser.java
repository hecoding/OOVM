package utils;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

/**
 * Una clase que hace de intermediario entre la entrada del usuario y la máquina virtual.
 * Configura la máquina conforme a dicha entrada.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class ArgsParser {
	private static final int _BATCH_MODE = 0;
	private static final int _INTER_MODE = 1;
	private static final int _WINDOW_MODE = 2;
	private static final int _NULL_STREAM = 3;
	private static final int _STD_STREAM = 4;
	private static final int _FILE_STREAM = 5;
	private Options options;
	private boolean interactiveMode, enteredProgramFile, helpAsked;
	private static int executionMode = _INTER_MODE;
	private static int inStreamMode = _NULL_STREAM;
	private static int outStreamMode = _NULL_STREAM;
	private static String inStreamFileName = null;
	private static String outStreamFileName = null;
	private static String programFileName = null;
	
	@SuppressWarnings("static-access")
	public ArgsParser() {
		this.interactiveMode = false;
		this.enteredProgramFile = false;
		this.helpAsked = false;
		this.options = new Options();
		
		options.addOption ("h", "help", false, "Muestra esta ayuda.");
		
		options.addOption (OptionBuilder.withLongOpt ("asm")
                						.withDescription( "Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch." )
                						.hasArg()
                						.withArgName("asmfile")
                						.create("a") );
		
		options.addOption (OptionBuilder.withLongOpt ("in")
										.withDescription( "Entrada del programa de la maquina-p." )
										.hasArg()
										.withArgName("infile")
										.create("i") );
		
		options.addOption (OptionBuilder.withLongOpt ("mode")
										.withDescription( "Modo de funcionamiento (batch | interactive). Por defecto, batch." )
										.hasArg()
										.withArgName("mode")
										.create("m") );
		
		options.addOption (OptionBuilder.withLongOpt ("out")
										.withDescription( "Fichero donde se guarda la salida del programa de la maquina-p." )
										.hasArg()
										.withArgName("outfile")
										.create("o") );
	}
	
	/**
	 * Parsea los argumentos de entrada del programa y configura la entrada/salida de la máquina virtual.
	 * @param args Argumentos de entrada.
	 * @return Dirección del programa fuente.
	 * @throws ParseException Si ha habido algún error al parsear los argumentos.
	 */
	public void parse (String[] args) throws ParseException {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse( this.options, args);
			
		if (cmd.hasOption("h") || cmd.hasOption("help")) {
			this.helpAsked = true;
		}
		else {
			
			if (!cmd.hasOption("m") || cmd.getOptionValue("m").equalsIgnoreCase ("interactive")) {
				if (!cmd.hasOption("a"))
					programFileName = null;
				else {
					this.enteredProgramFile = true;
					programFileName = cmd.getOptionValue("a");
				}
				
				if (!cmd.hasOption("in")) {
					inStreamMode = _NULL_STREAM;
					inStreamFileName = null;
				}
				else {
					if (cmd.getOptionValue("in").length() == 0) throw new MissingArgumentException (this.options.getOption("in"));
					
					inStreamMode = _FILE_STREAM;
					inStreamFileName =  cmd.getOptionValue("in");
				}
				
				if (!cmd.hasOption("out")) {
					outStreamMode = _NULL_STREAM;
					outStreamFileName = null;
				}
				else {
					if (cmd.getOptionValue("out").length() == 0) throw new MissingArgumentException (this.options.getOption("out"));
					
					outStreamMode = _FILE_STREAM;
					outStreamFileName = cmd.getOptionValue("out");
				}
				
				executionMode = _INTER_MODE;
				this.interactiveMode = true;
			}
			else if (cmd.getOptionValue("m").equalsIgnoreCase ("batch")) {
				if (!cmd.hasOption("a"))
					throw new MissingOptionException ("Fichero ASM no especificado.");
				
				if (!cmd.hasOption("in")) {
					inStreamMode = _STD_STREAM;
					inStreamFileName = null;
				}
				else {
					if (cmd.getOptionValue("in").length() == 0) throw new MissingArgumentException (this.options.getOption("in"));
					
					inStreamMode = _FILE_STREAM;
					inStreamFileName = cmd.getOptionValue("in");
				}
				
				if (!cmd.hasOption("out")) {
					outStreamMode = _STD_STREAM;
					outStreamFileName = null;
				}
				else {
					if (cmd.getOptionValue("out").length() == 0) throw new MissingArgumentException (this.options.getOption("out"));
					
					outStreamMode = _FILE_STREAM;
					outStreamFileName = cmd.getOptionValue("out");
				}
				
				programFileName = cmd.getOptionValue("a");
				executionMode = _BATCH_MODE;
				this.enteredProgramFile = true;
			}
			else if (cmd.getOptionValue("m").equalsIgnoreCase ("window")) {
				if (!cmd.hasOption("a"))
					throw new MissingOptionException ("Fichero ASM no especificado.");
				
				if (!cmd.hasOption("in")) {
					inStreamMode = _NULL_STREAM;
					inStreamFileName = null;
				}
				else {
					if (cmd.getOptionValue("in").length() == 0) throw new MissingArgumentException (this.options.getOption("in"));
					
					inStreamMode = _FILE_STREAM;
					inStreamFileName =  cmd.getOptionValue("in");
				}
				
				if (!cmd.hasOption("out")) {
					outStreamMode = _NULL_STREAM;
					outStreamFileName = null;
				}
				else {
					if (cmd.getOptionValue("out").length() == 0) throw new MissingArgumentException (this.options.getOption("out"));
					
					outStreamMode = _FILE_STREAM;
					outStreamFileName = cmd.getOptionValue("out");
				}
				
				programFileName = cmd.getOptionValue("a");
				executionMode = _WINDOW_MODE;
			}
			else throw new UnrecognizedOptionException ("Modo incorrecto (parámetro -m|--mode)");
		}
		
	}
	
	public void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp ("tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]", this.options);
	}
	
	public boolean isInteractiveMode() {
		
		return this.interactiveMode;
	}
	
	public int getExecutionMode() {
		return executionMode;
	}
	
	public int getInStreamMode() {
		return inStreamMode;
	}

	public int getOutStreamMode() {
		return outStreamMode;
	}

	public String getInStreamFileName() {
		return inStreamFileName;
	}

	public String getOutStreamFileName() {
		return outStreamFileName;
	}

	public String getProgramFileName() {
		return programFileName;
	}

	public boolean enteredProgramFile() {
		
		return this.enteredProgramFile;
	}
	
	public boolean helpAsked() {
		
		return this.helpAsked;
	}
}
