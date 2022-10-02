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
    
    /** @brief Constructor EventoExpositor.
     * 
     * Guarda un nuevo expositor en la clase.
     * @param e Expositor nuevo a guardar en la clase.
     * @param et Tipo de evento que se ejecuta.
     * */
    public EventoExpositor(EventType<? extends Event> et, Expositor e) {
        super(et);
        this.e1 = e;

    }
    
    /** @brief Constructor EventoExpositor.
     * 
     * Guarda un nuevo expositor en la clase y la antigua persona.
     * @param e1 Expositor antigup a guardar en la clase.
     * @param e2 Expositor nuevp a guardar en la clase .
     * @param et Tipo de evento que se ejecuta.
     * */
    public EventoExpositor(EventType<? extends Event> et, Expositor e1, Expositor e2) {
    	super(et);
    	this.e1 = e1;
    	this.e2 = e2;
    }
    
    /**
     * @return Atributo clase Expositor que contiene el expositor guardado.
     * */
    public Expositor getExpositor() {
        return e1;
    }
    
    /**
     * @return Atributo clase Expositor que contiene el expositor previo cuando se ha guardado un nuevo Expositor.
     * */
    public Expositor getExpositorAntiguo() {
    	return e1;
    }
    
    /**
     * @return Atributo clase Expositor que contiene al nuevo expositor guardado. 
     * */
    public Expositor getExpositorNuevo() {
    	return e2;
    }
}
