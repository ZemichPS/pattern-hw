package service;

import model.api.Command;

import java.util.ArrayList;
import java.util.List;

public class RemoteController {
    private List<Command> commands = new ArrayList<Command>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void execute(Command command) {
        command.execute();
    }

    public void executeAll() {
        commands.forEach(Command::execute);
    }
}
