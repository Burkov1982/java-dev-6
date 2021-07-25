package ua.goit.command;

import ua.goit.view.ConsoleManager;

public class UtilMessages implements Command{
    private final ConsoleManager consoleManager;

    private static final String menuMessage = """
                        
            Enter the command from the list below:
            - create
            - read
            - update
            - delete
            
            To finish current session, enter 'exit' 
            """;

    public UtilMessages(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    public void menuMessage(){
        consoleManager.write(menuMessage);
    }

    @Override
    public String commandName() {
        return "util";
    }

    @Override
    public void process() {

    }
}
