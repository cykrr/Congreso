package Gui;

import Congreso.Expositor;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoExpositor extends Event {
	
    public static final EventType<EventoExpositor> CREAR_EXPOSITOR = 
        new EventType<>(EventoExpositor.ANY, "CREAR_EXPOSITOR");

    public static final EventType<EventoExpositor> EDITAR_EXPOSITOR =
        new EventType<>(EventoExpositor.ANY, "EDITAR_EXPOSITOR");

    public static final EventType<EventoExpositor> ELIMINAR_EXPOSITOR =
        new EventType<>(EventoExpositor.ANY, "ELIMINAR_EXPOSITOR");

    private Expositor e1;
    private Expositor e2;

    public EventoExpositor(EventType<? extends Event> et, Expositor e) {
        super(et);
        this.e1 = e;

    }
    
    public EventoExpositor(EventType<? extends Event> et, Expositor e1, Expositor e2) {
    	super(et);
    	this.e1 = e1;
    	this.e2 = e2;
    }

    public Expositor getExpositor() {
        return e1;
    }
    
    public Expositor getExpositorAntiguo() {
    	return e1;
    }
    
    public Expositor getExpositorNuevo() {
    	return e2;
    }
}
