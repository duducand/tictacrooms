package es.duducand.tic.tac.rooms.persistance.dao.hibernate;



import es.duducand.tic.tac.rooms.persistance.dao.HabitacionDAO;
import es.duducand.tic.tac.rooms.persistance.dao.hibernate.GenericHibernateDAO;
import es.duducand.tic.tac.rooms.persistance.dao.model.Habitacion;

public class HabitacionDAOHibernate extends GenericHibernateDAO<Habitacion, Integer> 
                                 implements HabitacionDAO {

	/**
     * 
     */
    private static final long serialVersionUID = -7809040601615265015L;
	
}
