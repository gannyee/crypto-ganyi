package koal.main;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Console {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		Options options = new Options();
		
		options.addOption("h",false,"Print help for this application");
		options.addOption("u",true,"The username to use");
		options.addOption("dsn",true,"The data source to use");
		
		CommandLineParser parser = new BasicParser();
		CommandLine cl = parser.parse(options, args);
		
		if( cl.hasOption('h') ) {
            HelpFormatter f = new HelpFormatter();
            f.printHelp("OptionsTip", options);
        }
        else {
            System.out.println(cl.getOptionValue("u"));
            System.out.println(cl.getOptionValue("dsn"));
        }
 	}

}
