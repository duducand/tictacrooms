package es.duducand.tic.tac.rooms.persistance.dao.hibernate;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.duducand.tic.tac.rooms.persistance.dao.UsuarioDAO;
import es.duducand.tic.tac.rooms.persistance.dao.hibernate.utils.DbunitUtils;
import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;



@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration( locations={"classpath:**/spring-context-database.xml", "classpath:**/spring-context-seguridad.xml"})
public class UsuarioDAOHibernateTest extends TestCase  {
	
    private static Integer idBorrar;

	@Autowired
	public UsuarioDAO usuarioDAO;
	
	/** Logger available to subclasses */  
	private final Log logger = LogFactory.getLog(getClass());
	private final String fichero_datos = "test-usuario-dataset.xml"; 

	DbunitUtils dbunitUtils = new DbunitUtils();
   
	@Test
	public void testGetById() {
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-context-database.xml");
        usuarioDAO = (UsuarioDAO) ctx.getBean("usuarioDAO");

	    Integer id = null;
	    
		String query1 = "SELECT * FROM USUARIO";
		ITable result = dbunitUtils.executeQueryOperation(query1);
		logger.debug("Recuperados => " + result.getRowCount());
		Assert.assertNotNull(result);

		try {
			id = (Integer)result.getValue(0, "id");
		} catch (DataSetException e) {
			logger.debug(e.getMessage());
			//e.printStackTrace();
		}
		
		usuarioDAO.getById(id, false);


	}
	
	@Test
	public void testGetAll() {
	    
		String query = "SELECT * FROM USUARIO";
		ITable result = dbunitUtils.executeQueryOperation(query);
		int expectedResult = result.getRowCount();
	    
		List<Usuario> usuarios =  usuarioDAO.getAll();
		Assert.assertEquals(expectedResult, usuarios.size());
		
	}

//	En la aplicación no se hace uso de la utilidad getByExample
//		
//	@Test
//	public void testGetByExample() {
//		int expectedResult = 1;
//		
//		Usuario test = new Usuario();
//		test.setLogin("usuario1");
//		List<Usuario> results = usuarioDAO.getByExample(test);
//		
//		Assert.assertEquals(expectedResult, results.size());
//	}

//	@Test
//	public void testSave() {
//	    
//		int expectedResult = 1;
//		
//		Usuario usu = new Usuario();
//		String usuarioStr = "Prueba";
//		usu.setLogin(usuarioStr);
//		Usuario usuRes = usuarioDAO.save(usu);
//		idBorrar = usuRes.getId();
//		String query = "SELECT * FROM USUARIO WHERE login = '" + usuarioStr + "'" ;
//		ITable result = dbunitUtils.executeQueryOperation(query);
//		int registros = result.getRowCount();
//		Assert.assertNotNull(usuRes);
//		Assert.assertEquals(expectedResult, registros);
//		
//		logger.info("Recuperando usuario con login: " + usuarioStr);
//		Usuario usuRecuperado = usuarioDAO.getUsuarioByLogin(usuarioStr);
//		logger.info("Recuperado usuario con login: " + usu.getLogin());
//		Assert.assertNotNull(usuRecuperado);
//		Assert.assertEquals(usuarioStr, usuRecuperado.getLogin());
//	}

//	@Test
//	public void testGetUsuarioByLogin() {
//		logger.info("Iniciando test testGetUsuarioByLogin");
//		
//		logger.info("Recuperando usuario con id: " + idBorrar);
//		Usuario usu= usuarioDAO.getById(idBorrar, true);
//		logger.info("Recuperado usuario: " + usu.getLogin());
//		Assert.assertNotNull(usu);
//		
//		logger.info("Recuperando usuario con login: " + usu.getLogin());
//		Usuario usuRecuperado = usuarioDAO.getUsuarioByLogin(usu.getLogin());
//		logger.info("Recuperado usuario con login: " + usu.getLogin());
//		Assert.assertNotNull(usuRecuperado);
//		Assert.assertEquals(usu.getLogin(), usuRecuperado.getLogin());
//	}
	
//	@Test
//	public void testDelete() {
//	    Usuario usu= usuarioDAO.getById(idBorrar, false);
//	    usuarioDAO.delete(usu);
//        String query2 = "SELECT * FROM USUARIO WHERE id="+idBorrar;
//        ITable result = dbunitUtils.executeQueryOperation(query2);
//        assertEquals(0, result.getRowCount());
//	}

	@Before
	public void setUp() throws Exception {
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-context-database.xml");

	    dbunitUtils.executeSetUpOperation(fichero_datos);
	}

	@After
	public void tearDown() throws Exception {
		//dbunitUtils.executeNone();
	    dbunitUtils.executeTearDownOperation(fichero_datos);

	    usuarioDAO = null;
    }

}
