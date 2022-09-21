package Gui;

import Congreso.Presentacion;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoPresentacion extends Event {
    public static final EventType<EventoPresentacion> ANY = new EventType<>(Event.ANY, "ANY");

    public static final EventType<EventoPresentacion> CREAR_PRESENTACION = 
        new EventType<>(EventoPresentacion.ANY, "CREAR_PRESENTACION");

    public static final EventType<EventoPresentacion> EDITAR_PRESENTACION =
        new EventType<>(EventoPresentacion.ANY, "EDITAR_PRESENTACION");

    public static final EventType<EventoPresentacion> ELIMINAR_PRESENTACION =
        new EventType<>(EventoPresentacion.ANY, "ELIMINAR_PRESENTACION");

    Presentacion presentacion;

    public EventoPresentacion(EventType<? extends Event> et, Presentacion p) {
        super(et);
        this.presentacion = p;

    }

    public Presentacion getPresentacion() {
        return presentacion;
    }
}
