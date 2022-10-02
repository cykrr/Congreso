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
    
    /** @brief Constructor EventoPersona.
     * 
     * Guarda una nueva persona en la clase.
     * @param p Persona nueva a guardar en la clase.
     * @param et Tipo de evento que se ejecuta.
     * */
    public EventoPersona(EventType<? extends Event> et, Persona p) {
        super(et);
        this.p1 = p;
    }
    
    /** @brief Constructor EventoPersona.
     * 
     * Guarda una nueva persona en la clase y la antigua persona.
     * @param p1 Persona antigua a guardar en la clase.
     * @param p2 Persona nueva a guardar en la clase.
     * @param et Tipo de evento que se ejecuta.
     * */
    public EventoPersona(EventType<? extends Event> et, Persona p1, Persona p2) {
    	super(et);
    	this.p1 = p1;
    	this.p2 = p2;
    }
    
    /**
     * @return Atributo clase Persona que contiene la persona guardada.
     * */
    public Persona getPersona() {
        return p1;
    }
    
    /**
     * @return Atributo clase Persona que contiene la persona previa cuando se ha guardado una nueva Persona.
     * */
    public Persona getPersonaAntigua() {
    	return p1;
    }
    
    /**
     * @return Atributo clase Persona que contiene la nueva persona guardada.
     * */
    public Persona getPersonaNueva() {
    	return p2;
    }
}
