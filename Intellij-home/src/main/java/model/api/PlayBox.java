package model.api;

public interface PlayBox extends Device{
    void play();
    void pause();
    void stop();
    void setVolume(int volume);
}
