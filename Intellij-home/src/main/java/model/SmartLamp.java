package model;

import lombok.Getter;
import lombok.Setter;
import model.api.Connection;
import model.api.AbstractDevice;
import model.api.Connectivity;
import model.api.Lamp;

import java.util.UUID;

@Setter
@Getter
public class SmartLamp extends AbstractDevice implements Lamp{

    private Color color = Color.WHITE;
    private int brightness = 70;

    public SmartLamp(UUID id, String name, String ipAddress, Connectivity connectivity) {
        super(id, name, ipAddress, connectivity);
    }

    @Override
    public void turnOn() {
        Connection connect = getConnectivity().connect(this);
        connect.sendCommand("turn ON");
    }

    @Override
    public void turnOff() {
        Connection connect = getConnectivity().connect(this);
        connect.sendCommand("turn OFF");
    }

    public void setColor(Color color) {
        this.color = color;
        Connection connect = getConnectivity().connect(this);
        connect.sendCommand("set color=" + color.toString());
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        Connection connect = getConnectivity().connect(this);
        connect.sendCommand("set brightness=" + brightness);
    }
}
