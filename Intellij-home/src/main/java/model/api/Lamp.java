package model.api;

import model.Color;

public interface Lamp extends Device {
    void setBrightness(int brightness);
    void setColor(Color color);
}
