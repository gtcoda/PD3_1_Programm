package editor;

import com.beust.jcommander.*;

public class CommandLine {
    @Parameter(names = "-batchSize",
            description = "JDBC batch size",
            required=false)

    public int jdbc_batch_size=2500;

    @Parameter(names = {"--fileinput","-fi"},
            description = "Imput file",
            required=true)

    public String fileinput;

    @Parameter(names = {"--fileoutput","-fo"},
            description = "Imput file",
            required=false)

    public String fileoutput;

    @Parameter(names = {"--clipper","-c"},
            description = "Encryption/Decryption",
            required=false)

    public String clipper;


    @Parameter(names = {"--key","-k"},
            description = "Key",
            required=false)

    public String Key;



    @Parameter(names={"-h", "--help"},
            description="Help/Usage",
            help=true)
    public boolean help;
}
