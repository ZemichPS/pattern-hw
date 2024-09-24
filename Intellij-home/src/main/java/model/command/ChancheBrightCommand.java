package model.command;

import model.api.Command;
import model.api.Lamp;

public class ChancheBrightCommand<T extends Lamp> implements Command {
    private T lamp;
    private int brightness;

    public ChancheBrightCommand(T lamp, int brightness) {
        this.lamp = lamp;
        this.brightness = brightness;
    }

    @Override
    public void execute() {
        lamp.setBrightness(brightness);
    }
}
