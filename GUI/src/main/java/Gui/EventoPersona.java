package Gui;

import Congreso.Persona;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoPersona extends Event {
    // public static final EventType<EventoPersona> ANY = new EventType<>(Event.ANY, "ANY");

    public static final EventType<EventoPersona> CREAR_PERSONA = 
        new EventType<>(EventoPersona.ANY, "CREAR_PERSONA");

    public static final EventType<EventoPersona> EDITAR_PERSONA =
        new EventType<>(EventoPersona.ANY, "EDITAR_PERSONA");

    public static final EventType<EventoPersona> ELIMINAR_PERSONA =
        new EventType<>(EventoPersona.ANY, "ELIMINAR_PERSONA");

    private Persona p1;
    private Persona p2;

    public EventoPersona(EventType<? extends Event> et, Persona p) {
        super(et);
        this.p1 = p;
    }
    
    public EventoPersona(EventType<? extends Event> et, Persona p1, Persona p2) {
    	super(et);
    	this.p1 = p1;
    	this.p2 = p2;
    }

    public Persona getPersona() {
        return p1;
    }
    
    public Persona getPersonaAntigua() {
    	return p1;
    }
    
    public Persona getPersonaNueva() {
    	return p2;
    }
}
