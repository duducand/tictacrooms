package es.duducand.tic.tac.rooms.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.duducand.tic.tac.rooms.persistance.dao.model.Habitacion;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;
import es.duducand.tic.tac.rooms.service.IHabitacionService;

//La anotacion Controller de Spring-mvc indica que esta clase implementa un controller dentro del patron MVC. 
//Un controller es una clase dedicada a manejar solicitudes y a devolver resultados. 
//Las solicitudes vienen indicadas bajo la anotacion RequestMapping, es decir, 
//cuando desde el cliente se solicite la url indicada en el Request mapping, dicha solicitud será procesada por este controller
@Controller
@RequestMapping(value = "/tabla/habitacion")
public class HabitacionListController extends BaseJsonListController<Habitacion> {

    private IHabitacionService habitacionService;
    
    // La anotacion permite que Spring automáticamente cuando detecte el componente 
    @Autowired
    public void setHabitacionService(IHabitacionService habitacionService) {
		this.habitacionService = habitacionService;
	}
    
    @Override
    protected PagedResult<Habitacion> getResult(PagedQuery query) {
    	return habitacionService.getHabitaciones(query);
    }
    

	@Override
    protected Habitacion getResultItem(String id) {
		return habitacionService.getHabitacion(Integer.parseInt(id));
    }
	
    @Override
    protected Map<String, Object> getMap(Habitacion habitacion) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("id",      habitacion.getId());
    	map.put("precio",  habitacion.getPrecio());
    	map.put("direccion",  habitacion.getDireccion());
    	
    	return map;
    }
}
