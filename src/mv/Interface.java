package mv;

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

import mv.mvSystem.MVSystem;
import mv.mvSystem.in.InStrategyFile;
import mv.mvSystem.in.InStrategyNada;
import mv.mvSystem.in.InStrategyStd;
import mv.mvSystem.out.OutStrategyFile;
import mv.mvSystem.out.OutStrategyNada;
import mv.mvSystem.out.OutStrategyStd;

/**
 * Una clase que hace de intermediario entre la entrada del usuario y la máquina virtual.
 * Configura la máquina conforme a dicha entrada.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class Interface {
	private Options options;
	private boolean interactiveMode, enteredProgramFile, helpAsked;
	
	@SuppressWarnings("static-access")
	public Interface() {
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
	public String parse (String[] args) throws ParseException {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse( this.options, args);
			
		if (cmd.hasOption("h") || cmd.hasOption("help")) {
			this.helpAsked = true;
		}
		else {
			
			if (!cmd.hasOption("m") || cmd.getOptionValue("m").equalsIgnoreCase ("interactive")) {
				if (cmd.hasOption("a"))
					this.enteredProgramFile = true;
				
				if (!cmd.hasOption("in"))
					MVSystem.in = new InStrategyNada();
				else {
					if (cmd.getOptionValue("in").length() == 0) throw new MissingArgumentException (this.options.getOption("in"));
					else
						MVSystem.in = new InStrategyFile (cmd.getOptionValue("in"));
				}
				
				if (!cmd.hasOption("out"))
					MVSystem.out = new OutStrategyNada();
				else {
					if (cmd.getOptionValue("out").length() == 0) throw new MissingArgumentException (this.options.getOption("out"));
					else
						MVSystem.out = new OutStrategyFile (cmd.getOptionValue("out"));
				}
				
				this.interactiveMode = true;
			}
			else if (cmd.getOptionValue("m").equalsIgnoreCase ("batch")) {
				if (!cmd.hasOption("a"))
					throw new MissingOptionException ("Fichero ASM no especificado.");
				
				if (!cmd.hasOption("in"))
					MVSystem.in = new InStrategyStd();
				else {
					if (cmd.getOptionValue("in").length() == 0) throw new MissingArgumentException (this.options.getOption("in"));
					else
						MVSystem.in = new InStrategyFile (cmd.getOptionValue("in"));
				}
				
				if (!cmd.hasOption("out"))
					MVSystem.out = new OutStrategyStd();
				else {
					if (cmd.getOptionValue("out").length() == 0) throw new MissingArgumentException (this.options.getOption("out"));
					else
						MVSystem.out = new OutStrategyFile (cmd.getOptionValue("out"));
				}
				
				this.enteredProgramFile = true;
			}
			else throw new UnrecognizedOptionException ("Modo incorrecto (parámetro -m|--mode)");
			
			return cmd.getOptionValue("a");
		}
		return null;
		
	}
	
	public void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp ("mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]", this.options);
	}
	
	public boolean isInteractiveMode() {
		
		return this.interactiveMode;
	}
	
	public boolean enteredProgramFile() {
		
		return this.enteredProgramFile;
	}
	
	public boolean helpAsked() {
		
		return this.helpAsked;
	}
}
