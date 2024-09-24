package model.command;

import model.Color;
import model.api.Command;
import model.api.Lamp;

public class ChangeLampColor<T extends Lamp> implements Command {

    private T lamp;
    private Color color;

    public ChangeLampColor(T lamp, Color color) {
        this.lamp = lamp;
        this.color = color;
    }

    @Override
    public void execute() {
        lamp.setColor(color);
    }
}
