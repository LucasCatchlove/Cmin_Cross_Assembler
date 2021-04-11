package components;

public class Options {

    private boolean listing = false;
    private boolean verbose = false;

    public Options(String[] args) {
        setOptions(args);
    }

    public boolean listingEnabled() {
        return listing;
    }

    public boolean verboseEnabled() {
        return verbose;
    }

    private void setOptions(String[] args) {
        for(String argument : args) {
            switch(argument) {
                case "-listing":
                    listing = true;
                    break;
                case "-verbose":
                    verbose = true;
                    break;
                case "-help":
                    printGuide();
            }
        }

    }
    private void printGuide() {
        System.out.println("\nCross-Assembler guide\n=====================\n");
        System.out.println("The proper command line formatting is as follows: \"source file\" \"-option1\" \"-option2\" \"-option3\"");
        System.out.println("The options can be entered in any order, however the source file name must be listed first\n");
        System.out.println("The \"-listing\" flag produces a source listing of the assembly program");
        System.out.println("The \"-verbose\" flag produces a source listing of the assembly program as well as its corresponding label table\n");
        System.out.println("=====================\n");
    }
}
