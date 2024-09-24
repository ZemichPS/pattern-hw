package service.impl;

import service.api.EventListener;
import java.util.*;

public class EventManager {

    private Map<String, List<EventListener<?>>> listeners = new HashMap<>();

    public EventManager(String... events) {
        Arrays.stream(events).forEach(event -> listeners.put(event, new ArrayList<>()));
    }

    public void subscribe(String eventType, EventListener<?> eventListener) {
        listeners.getOrDefault(eventType, new ArrayList<>()).add(eventListener);
    }

    public void unsubscribe(String eventType, EventListener<?> eventListener) {
        if (!listeners.containsKey(eventType)) {
            throw new RuntimeException("Event not subscribed");
        }
        listeners.get(eventType).remove(eventListener);
    }

    public <T> void publish(String eventType, T observable) {
        if (listeners.containsKey(eventType)) {
            listeners.get(eventType).forEach(eventListener -> {
                ((EventListener<T>) eventListener).update(eventType, observable);
            });
        }
    }
}
