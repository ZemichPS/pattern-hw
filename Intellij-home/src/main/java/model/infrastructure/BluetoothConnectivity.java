package model.infrastructure;

import model.api.Connection;
import model.api.Connectivity;
import model.api.AbstractDevice;

import java.time.LocalDateTime;

public class BluetoothConnectivity implements Connectivity {
    @Override
    public Connection connect(AbstractDevice abstractDevice) {
        return new BluetoothConnection(abstractDevice.getAddress(), abstractDevice.getName(), LocalDateTime.now());
    }
}
