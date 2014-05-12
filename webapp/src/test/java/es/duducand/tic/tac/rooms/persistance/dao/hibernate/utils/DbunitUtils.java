package es.duducand.tic.tac.rooms.persistance.dao.hibernate.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DbunitUtils {
	private IDataSet loadedDataSet;
	
	/** Logger available to subclasses */  
	private final Log logger = LogFactory.getLog(getClass());
	
	private IDatabaseConnection getNewDatabaseConnection() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tic-tac-roomsdb";
        String username = "root";
        String password = "root";

        Class.forName(driver);

        Connection jdbcConnection =  DriverManager.getConnection(url, username, password);  
	    return new DatabaseConnection(jdbcConnection);

	}
	
	@SuppressWarnings("deprecation")
	private IDataSet getDataSet(String fichero) throws Exception
	{
	  loadedDataSet = new FlatXmlDataSet(
	    this.getClass().getClassLoader().getResourceAsStream(fichero));
	  return loadedDataSet;
	}

	/**
	 * Returns the database operation executed in test setup.
	 */
	public void executeSetUpOperation(String fichero) 
	{
 	    try {  
		    DatabaseOperation.CLEAN_INSERT.execute(getNewDatabaseConnection(), getDataSet(fichero));
	    } catch (Exception ex) {
	    	logger.info("Excepci�n => " + ex.getMessage());
		} finally {  
		   logger.info("*** Finished inserting test data ***");  
		} 
	}

	/**
	 * Returns the database operation executed in test cleanup.
	 */
	public void executeTearDownOperation(String fichero) 
	{
 	    try {  
		    DatabaseOperation.DELETE.execute(getNewDatabaseConnection(), getDataSet(fichero));
	    } catch (Exception ex) {
	    	logger.info("Excepci�n => " + ex.getMessage());
		} finally {  
		   logger.info("*** Finished delete test data ***");  
		} 
	}

	public void executeNone() 
	{
	}
	
	public ITable executeQueryOperation(String query) 
	{
		ITable actualJoinData = null;
 	    try {  
 			actualJoinData = getNewDatabaseConnection().
 													createQueryTable("RESULT_NAME",
 													query); 
	    } catch (Exception ex) {
	    	logger.info("Excepcion => " + ex.getMessage());
		} finally {  
		   logger.info("*** Finished query test data ***");  
		}
		return actualJoinData;
	}


	/*
	public void testCheckDataLoaded() throws Exception
	{
	  assertNotNull(loadedDataSet);
	  int rowCount = loadedDataSet.getTable(TABLE_NAME).getRowCount();
	  assertEquals(2, rowCount);
	}

	public void testCompareDataSet() throws Exception
	{
	  IDataSet createdDataSet = getConnection().createDataSet(new String[]
	  {
	    TABLE_NAME
	  });
	  Assertion.assertEquals(loadedDataSet, createdDataSet);
	}
	*/

}
