package model.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Connection {
    private String protocolName;
    private String deviceAddress;
    private String deviceName;

    public Connection(String protocolName,
                      String deviceAddress,
                      String deviceName
    ) {
        this.protocolName = protocolName;
        this.deviceAddress = deviceAddress;
        this.deviceName = deviceName;
    }

    public void sendCommand(String command) {
        System.out.println("command: %s was sent to device name: %s".formatted(command, deviceName));
    }
}
