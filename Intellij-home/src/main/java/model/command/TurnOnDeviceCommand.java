package model.command;

import model.api.AbstractDevice;
import model.api.Command;
import model.api.Device;

public class TurnOnDeviceCommand<T extends Device> implements Command {
    private final T device;

    public TurnOnDeviceCommand(T device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}
