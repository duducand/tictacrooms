package es.duducand.tic.tac.rooms.persistance.dao.hibernate;



import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.duducand.tic.tac.rooms.persistance.dao.HabitacionDAO;
import es.duducand.tic.tac.rooms.persistance.dao.hibernate.utils.DbunitUtils;
import es.duducand.tic.tac.rooms.persistance.dao.model.Habitacion;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration( {"classpath:/spring-context-database.xml"}) 
public class HabitacionDAOHibernateTest extends TestCase{

	protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public HabitacionDAO dao;
    
    DbunitUtils dbunitUtils = new DbunitUtils();
   
    
    /******************************************** datos a cambiar en cada clase de prueba ********************************************************/
	private static int numeroEntidadesAlInicio;
    private static Habitacion nuevaEntidad = null;
    private static Integer nuevaEntidadIdentificador = null;
    private static Habitacion entidadGetByExample = null;
    private static String querySelectEntidad = "SELECT * FROM HABITACION WHERE id = ";
    List<Habitacion> listaEntidadesEnBd = null;
    
    private Integer obtenerIdentificadorNuevaEntidad(){
    	return nuevaEntidad.getId();
    }
    
    private void cambiarEntidadParaActualizar(Object obj){
    	final Habitacion other = (Habitacion)obj;
    	other.setPrecio("NombreCambio");
    }
    
    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-context-database.xml");
        dao = (HabitacionDAO) ctx.getBean("habitacionDAO");
        
        // esta es la entidad que primeramente almacenaremos en BBDD
        nuevaEntidad = new Habitacion();
        nuevaEntidad.setDireccion("Direccion nuevo");
        nuevaEntidad.setPrecio("Precio");
        
        // Buscaremos las entidades en BBDD que tengan estos atributos. Nos deberan salir al menos una entidad, que es la que insertamos al principio
        entidadGetByExample = new Habitacion();
        entidadGetByExample.setPrecio("PrecioNuevo");
    }
    /******************************************** datos a cambiar en cada clase de prueba ********************************************************/
        
    @Test
    public void testInicioGetAllEntidades(){
    	logger.info("Iniciando test testInicioGetAllEntidades");
    	
    	listaEntidadesEnBd = dao.getAll();
    	logger.info("Al comienzo de la prueba hay " + listaEntidadesEnBd.size() + " entidad/es en la BDD.");
    	assertNotNull(listaEntidadesEnBd); 
    	// almacenamos el numero de entidades al inicio de la prueba
    	this.numeroEntidadesAlInicio = listaEntidadesEnBd.size();
    }
    
    @Test
    public void testInsertEntidad(){        
        logger.info("Insertando en BDD la nueva entidad en BDD: " + nuevaEntidad.toString());
        nuevaEntidad = dao.merge(nuevaEntidad);

        System.out.println("Inserccion de la nueva entidad con id resultante => " + nuevaEntidad.getId());
        
        nuevaEntidadIdentificador = obtenerIdentificadorNuevaEntidad();
        // la nueva entidad tiene que tener un id generado por la BD
        assertNotNull(nuevaEntidadIdentificador);
    } 
    
    @Test
    public void testGetAllEntidadesDespuesDeInserccion(){
    	logger.info("Iniciando test testGetAllEntidadesDespuesDeInserccion");
    	
    	listaEntidadesEnBd = dao.getAll();
    	logger.info("Despues de la inserccion hay " + listaEntidadesEnBd.size() + " entidades en la BDD.");
    	// tiene que haber una entidad mas en la BDD
    	assertEquals(listaEntidadesEnBd.size(), this.numeroEntidadesAlInicio+1);
    }

   
    @Test
    public void testSelectEntidadById(){
    	logger.info("Iniciando test testSelectEntidadById");
    	
    	logger.info("Buscando la entidad almacenada con id: " + nuevaEntidadIdentificador);
    	nuevaEntidad = dao.getById(nuevaEntidadIdentificador, true);
    	
    	if (nuevaEntidad!=null){
        	logger.info("Recuperado de la BD la entidad insertada: " + nuevaEntidad.toString());
    	}else{
    		logger.info("No se ha podido recuperar de la BD la entidad insertada.");
    	}
    	
    	// tienen que recuperar la entidad almacenada al principio
    	assertEquals(nuevaEntidadIdentificador, obtenerIdentificadorNuevaEntidad());
    }
    
    @Test
    public void testGetByExample(){
    	logger.info("Iniciando test testGetByExample");
         
    	listaEntidadesEnBd = dao.getByExample(entidadGetByExample);
        logger.info("Lista de entidades encontradas debe ser al menos 1: " + listaEntidadesEnBd.toString());
        // tiene que haber obtenido por lo menos una lista con una entidad almacenada, la creada al principio
     	assertTrue("La lista de entidades encontradas con el metodo getByExample no ha encontrado al menos la entidad inicial.", listaEntidadesEnBd!=null && listaEntidadesEnBd.size()>0);
    }
    
    @Test
    public void testUpdateEntidad(){
    	logger.info("Iniciando test testUpdateEntidad cambiando id: " +nuevaEntidadIdentificador);
    	
    	Object entidadInicial = dao.getById(nuevaEntidadIdentificador, true);
    	
    	logger.info("Entidad obtenida con id=" + nuevaEntidadIdentificador+": " +entidadInicial.toString());
    	// se cambia algun aspecto de la entidad que se pretende actualizar en BBDD
    	cambiarEntidadParaActualizar(entidadInicial);
    	
    	logger.info("Entidad salvada a BBDD como: " +entidadInicial.toString());
    	// Cambiar la clases de parseo cada clase de pruebas 
        dao.save((Habitacion)entidadInicial);
        
        Object entidadActualizada = dao.getById(nuevaEntidadIdentificador, true);
        logger.info("Entidad recuperada de BBDD con id="+nuevaEntidadIdentificador+": " +entidadInicial.toString());
        
        // comprobamos que la entidad inicial cambiada por codigo y la misma entidad almacenada en BBDD son iguales
        assertEquals(entidadInicial,entidadActualizada);
    }
    
   @Test
    public void testDeleteEntidad(){
    	logger.info("Iniciando test testDeleteEntidad");
    	
    	Habitacion entidadInicial = dao.getById(nuevaEntidadIdentificador, true);
    	
        dao.delete(entidadInicial);
        
        querySelectEntidad += obtenerIdentificadorNuevaEntidad();
        ITable result = dbunitUtils.executeQueryOperation(querySelectEntidad);
        
        assertEquals(0, result.getRowCount());
    }
 
    
}