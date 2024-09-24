package model.infrastructure;

import model.api.Connection;
import model.api.Connectivity;
import model.api.AbstractDevice;

public class WifiConnectivity implements Connectivity {
    @Override
    public Connection connect(AbstractDevice abstractDevice) {
        return new WifiConnection(abstractDevice.getAddress(), abstractDevice.getName(), getSsid());
    }

    private String getSsid(){
        return "my-home";
    }
}
