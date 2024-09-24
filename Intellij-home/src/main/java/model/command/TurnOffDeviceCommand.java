package model.command;

import model.api.Command;
import model.api.Device;

public class TurnOffDeviceCommand<T extends Device> implements Command {
    private T device;

    public TurnOffDeviceCommand(T device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
