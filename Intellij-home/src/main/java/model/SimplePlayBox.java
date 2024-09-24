package model;

import model.api.AbstractDevice;
import model.api.Connection;
import model.api.Connectivity;
import model.api.PlayBox;

import java.util.UUID;

public class SimplePlayBox extends AbstractDevice implements PlayBox {
    private int volume = 30;

    public SimplePlayBox(UUID id, String name, String address, Connectivity connectivity) {
        super(id, name, address, connectivity);
    }

    @Override
    public void turnOn() {
        Connection connection = this.getConnectivity().connect(this);
        connection.sendCommand("Turn on with default");
    }

    @Override
    public void turnOff() {
        Connection connection = this.getConnectivity().connect(this);
        connection.sendCommand("Turn off");
    }

    @Override
    public void play() {
        Connection connection = this.getConnectivity().connect(this);
        connection.sendCommand("play");
    }

    @Override
    public void pause() {
        Connection connection = this.getConnectivity().connect(this);
        connection.sendCommand("play");
    }

    @Override
    public void stop() {
        Connection connection = this.getConnectivity().connect(this);
        connection.sendCommand("stop");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }
}
