package Gui;

import Congreso.Persona;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoPersona extends Event {
    public static final EventType<EventoPersona> ANY = new EventType<>(Event.ANY, "ANY");

    public static final EventType<EventoPersona> CREAR_PERSONA = 
        new EventType<>(EventoPersona.ANY, "CREAR_PERSONA");

    public static final EventType<EventoPersona> EDITAR_PERSONA =
        new EventType<>(EventoPersona.ANY, "EDITAR_PERSONA");

    public static final EventType<EventoPersona> ELIMINAR_PERSONA =
        new EventType<>(EventoPersona.ANY, "ELIMINAR_PERSONA");

    Persona persona;

    public EventoPersona(EventType<? extends Event> et, Persona p) {
        super(et);
        this.persona = p;

    }

    public Persona getPersona() {
        return persona;
    }
}
