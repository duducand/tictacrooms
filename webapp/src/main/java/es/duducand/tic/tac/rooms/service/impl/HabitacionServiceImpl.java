package es.duducand.tic.tac.rooms.service.impl;

import es.duducand.tic.tac.rooms.persistance.dao.HabitacionDAO;
import es.duducand.tic.tac.rooms.persistance.dao.model.Habitacion;
import es.duducand.tic.tac.rooms.persistance.dao.security.EstadoUsuario;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;
import es.duducand.tic.tac.rooms.service.IHabitacionService;

@SuppressWarnings("unused")
public class HabitacionServiceImpl implements IHabitacionService {
	private static final long serialVersionUID = 1L;
	
	
	private EstadoUsuario                    estadoUsuario;
	private HabitacionDAO habitacionDAO;

	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
	public void setHabitacionDAO(HabitacionDAO habitacionDAO) {
		this.habitacionDAO = habitacionDAO;
	}
	
	public PagedResult<Habitacion> getHabitaciones(PagedQuery query){		
		return new PagedResult<Habitacion>(
				habitacionDAO.countByPagedQuery(query),
				query.getStart(),
				habitacionDAO.findByPagedQuery(query));
	}
	
	public Habitacion getHabitacion(int id){
		return habitacionDAO.getById(id, true);
	}

    public Habitacion createHabitacion() {
        Habitacion actor= new Habitacion();
        return actor;
    }

    public void saveHabitacion(Habitacion habitacion) {
        habitacionDAO.save(habitacion);
    }

}
