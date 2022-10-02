package Gui;

import Congreso.Presentacion;
import javafx.event.Event;
import javafx.event.EventType;

public class EventoPresentacion extends Event {

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
    
    /** @brief Constructor EventoPresentacion.
     * 
     * Guarda un nuevo expositor en la clase.
     * @param p Presentacion nueva a guardar en la clase.
     * @param et Tipo de evento que se ejecuta.
     * */
    public EventoPresentacion(EventType<? extends Event> et, Presentacion p) {
        super(et);
        this.p1 = p;
    }
    
    /** @brief Constructor EventoPresentacion.
     * 
     * Guarda una nueva presentacion en la clase y la antigua presentacion.
     * @param p1 Presentacion antigua a guardar en la clase.
     * @param p2 Presentacion nueva a guardar en la clase.
     * @param et Tipo de evento que se ejecuta.
     * */
    public EventoPresentacion(EventType<? extends Event> et, Presentacion p1,
            Presentacion p2) {
        super(et);
        this.p1 = p1;
        this.p2 = p2;
    }
    
    /**
     * @return Atributo clase Presentacion que contiene la presentacion guardada.
     * */
    public Presentacion getPresentacion() {
        return p1;
    }
    
    /**
     * @return Atributo clase Presentacion que contiene la presentacion previo cuando se ha guardado una nueva Presentacion.
     * */
    public Presentacion getPresentacionAntigua() {
        return p1;
    }
    
    /**
     * @return Atributo clase Presentacion que contiene la nueva presentacion guardada. 
     * */
    public Presentacion getPresentacionNueva() {
        return p2;
    }
}
