package Gui;

import Congreso.Expositor;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoExpositor extends Event {
    // public static final EventType<EventoExpositor> ANY = new EventType<>(Event.ANY, "ANY");

    public static final EventType<EventoExpositor> CREAR_EXPOSITOR = 
        new EventType<>(EventoExpositor.ANY, "CREAR_EXPOSITOR");

    public static final EventType<EventoExpositor> EDITAR_EXPOSITOR =
        new EventType<>(EventoExpositor.ANY, "EDITAR_EXPOSITOR");

    public static final EventType<EventoExpositor> ELIMINAR_EXPOSITOR =
        new EventType<>(EventoExpositor.ANY, "ELIMINAR_EXPOSITOR");

    Expositor expositor;

    public EventoExpositor(EventType<? extends Event> et, Expositor e) {
        super(et);
        this.expositor = e;

    }

    public Expositor getExpositor() {
        return expositor;
    }
}
