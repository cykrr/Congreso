package Gui;

import Congreso.Presentacion;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoPresentacion extends Event {
    public static final EventType<EventoPresentacion> ANY = new EventType<>(
            Event.ANY, "ANY");

    public static final EventType<EventoPresentacion> CREAR_PRESENTACION = new EventType<>(
            EventoPresentacion.ANY,
            "CREAR_PRESENTACION");

    public static final EventType<EventoPresentacion> EDITAR_PRESENTACION = new EventType<>(
            EventoPresentacion.ANY,
            "EDITAR_PRESENTACION");

    public static final EventType<EventoPresentacion> ELIMINAR_PRESENTACION = new EventType<>(
            EventoPresentacion.ANY,
            "ELIMINAR_PRESENTACION");
    
    public static final EventType<EventoPresentacion> MODIFICAR_ASISTENTES = new EventType<>(
            EventoPresentacion.ANY,
            "MODIFICAR_ASISTENTES");

    private Presentacion p1;
    private Presentacion p2;

    public EventoPresentacion(EventType<? extends Event> et, Presentacion p) {
        super(et);
        this.p1 = p;
    }

    public EventoPresentacion(EventType<? extends Event> et, Presentacion p1,
            Presentacion p2) {
        super(et);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Presentacion getPresentacion() {
        return p1;
    }

    public Presentacion getPresentacionAntigua() {
        return p1;
    }

    public Presentacion getPresentacionNueva() {
        return p2;
    }
}
