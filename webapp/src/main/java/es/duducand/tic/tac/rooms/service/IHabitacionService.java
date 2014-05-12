package es.duducand.tic.tac.rooms.service;

import java.io.Serializable;

import es.duducand.tic.tac.rooms.persistance.dao.model.Habitacion;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;


public interface IHabitacionService extends Serializable {

	public PagedResult<Habitacion> getHabitaciones(PagedQuery query);
	public Habitacion getHabitacion(int id);
	public Habitacion createHabitacion();
	public void saveHabitacion(Habitacion habitacion);
}
