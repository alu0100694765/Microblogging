/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: Connection.java
 * Version: 1.0
 * Date: 04/12/2015
 * Description:
 * Copyright: Copyright 200X Sawan J. Kapai Harpalani
 *			 
 *			 This file is part of Math Attack.
 *
 *			 MicroBlogging is free software: you can redistribute it 
 *			 and/or modify it under the terms of the GNU General	
 *   		 Public License as published by the Free Software 
 *		     Foundation, either version 3 of the License,
 *			 or (at your option) any later version.
 *
 *
 *			 MicroBlogging is distributed in the hope that it will 
 *			 be useful, but WITHOUT ANY WARRANTY; without even 
 *			 the implied warranty of MERCHANTABILITY or FITNESS
 *			 FOR A PARTICULAR PURPOSE. See the GNU General Public
 *			 License for more details.
 *			
 *			 You should have received a copy of the GNU General
 *			 Public License along with MicroBlogging. If not, see
 *			 http://www.gnu.org/licenses/.
 */
package mongo;

import java.util.ArrayList;
import java.util.List;

import utility.Utility;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;


/**
 * The Class Connection.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class Connection {

	/** The instance. */
	private static Connection instance = null;
	
	/** The client. */
	protected final MongoClient client;
	
	private final DB database;

	
	/**
	 * Instantiates a new connection.
	 */
	@SuppressWarnings("deprecation")
	protected Connection() {
		// Connect to the MongoDB store
		// If the user has defined the user name, password and address in the MongoParameters class then it will you credentials otherwise just connect
		if (MongoParameters.USERNAME != null && MongoParameters.PASSWORD != null && MongoParameters.ADDRESS != null) {
			List<MongoCredential> credentials =  new ArrayList<MongoCredential>();
			credentials.add(MongoCredential.createCredential(MongoParameters.USERNAME, MongoParameters.DATABASE, Utility.stringToChar(MongoParameters.PASSWORD)));
			client =  new MongoClient(MongoParameters.ADDRESS, credentials);
		} else {
			client = new MongoClient(MongoParameters.HOST_NAME, MongoParameters.PORT);
		}
		
		// Connect to the database
		database = client.getDB(MongoParameters.DATABASE);

	}
	
	/**
	 * Gets the single instance of Connection.
	 *
	 * @return single instance of Connection
	 */
	public static Connection getInstance() {
		if (instance == null) {
	      instance = new Connection();
	    }
	    return instance;
	}

	/**
	 * @return the database
	 */
	public DB getDatabase() {
		return database;
	}
	
}
