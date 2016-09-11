package org.krams.tutorial.mongo;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class MongoDBFactory {
	protected static Logger logger = LoggerFactory.getLogger("mongo");
	
	private static Mongo m;
	
	// Make sure no one can instantiate our factory
	private MongoDBFactory() {}
	
	// Return an instance of Mongo
	public static Mongo getMongo() throws UnknownHostException {
		logger.debug("Retrieving MongoDB");
		if (m == null) {
			try {
				m = new Mongo( "localhost" , 27017 );
			} catch (MongoException e) {
				logger.error(""+e);
			}
		}
		
		return m;
	}
	
	// Retrieve a db
	public static DB getDB(String dbname) {
		logger.debug("Retrieving db: " + dbname);
		try {
			return getMongo().getDB(dbname);
		} catch (UnknownHostException e) {
			logger.error("Unknown Host Exception : "+e.getMessage());
		}
		return null;
	}
	
	// Retrieve a collection
	public static DBCollection getCollection(String dbname, String collection) {
		logger.debug("Retrieving collection: " + collection);
		return getDB(dbname).getCollection(collection);
	}
}
