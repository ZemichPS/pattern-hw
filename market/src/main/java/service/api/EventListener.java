package service.api;

public interface EventListener<T> {
    void update(String eventType, T observable);
}
