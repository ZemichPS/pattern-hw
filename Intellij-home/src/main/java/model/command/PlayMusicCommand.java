package model.command;

import model.api.Command;
import model.api.PlayBox;

public class PlayMusicCommand<T extends PlayBox> implements Command {

    private T playBox;
    public PlayMusicCommand(T playBox) {
        this.playBox = playBox;
    }

    @Override
    public void execute() {
        playBox.play();
    }
}
