package model.infrastructure;

import lombok.Getter;
import lombok.Setter;
import model.api.Connection;

import java.time.LocalDateTime;

@Getter
@Setter
public class BluetoothConnection extends Connection {

    private LocalDateTime connectedAt;

    public BluetoothConnection(String deviceAddress,
                               String deviceName,
                               LocalDateTime connectedAt
    ) {
        super("bluetooth", deviceAddress, deviceName);
        this.connectedAt = connectedAt;
    }
}
