package model.infrastructure;

import lombok.Getter;
import lombok.Setter;
import model.api.Connection;

@Getter
@Setter
public class WifiConnection extends Connection {

    private String SSID;

    public WifiConnection(String deviceAddress, String deviceName, String SSID) {
        super("wifi", deviceAddress, deviceName);
        this.SSID = SSID;
    }
}
