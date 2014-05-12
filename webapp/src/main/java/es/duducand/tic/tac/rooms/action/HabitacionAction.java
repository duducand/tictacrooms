package es.duducand.tic.tac.rooms.action;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import es.duducand.tic.tac.rooms.persistance.dao.model.Habitacion;
import es.duducand.tic.tac.rooms.service.IHabitacionService;

public class HabitacionAction extends MultiAction{
    private IHabitacionService habitacionService;

    public IHabitacionService getHabitacionService() {
        return habitacionService;
    }

    public void setHabitacionService(
            IHabitacionService actorService) {
        this.habitacionService = actorService;
    }

    public Event saveActor(Habitacion habitacion, RequestContext context){
        try{
            habitacionService.saveHabitacion(habitacion);
            return result("GUARDADO");
        }catch(Exception e){
            e.printStackTrace();
            return result("ERROR");
        }
    }
    
}
