import model.*;
import model.api.*;
import model.command.ChancheBrightCommand;
import model.command.ChangeLampColor;
import model.command.TurnOnDeviceCommand;
import model.infrastructure.BluetoothConnectivity;
import model.infrastructure.WifiConnectivity;
import service.RemoteController;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Lamp badroomSmartLamp = new SmartLamp(
                UUID.randomUUID(),
                "Samsung multiColor smart lamp",
                "172.16.74.7",
                new WifiConnectivity()
        );

        PlayBox playBox = new SimplePlayBox(
                UUID.randomUUID(),
                "JBL party music box",
                "00:1A:7D:DA:71:13",
                new BluetoothConnectivity()
        );

        Command turnOnMusicCommand = new TurnOnDeviceCommand<>(playBox);
        Command turnOnLightCommand = new TurnOnDeviceCommand<>(badroomSmartLamp);
        Command setMaxBrightCommand = new ChancheBrightCommand<>(badroomSmartLamp, 100);
        Command setLampColor = new ChangeLampColor<>(badroomSmartLamp, Color.YELLOW);

        RemoteController remoteController = new RemoteController();
        remoteController.addCommand(turnOnMusicCommand);
        remoteController.addCommand(turnOnLightCommand);
        remoteController.addCommand(setMaxBrightCommand);
        remoteController.addCommand(setLampColor);
        remoteController.executeAll();

    }
}
