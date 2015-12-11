/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryUniqueUser.java
 * Version: 1.0
 * Date: 10/12/2015
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
package queries;

import java.util.List;

import mongo.MongoParameters;
import mongo.RecordsParameters;
import mongo.query.IQuery;
import mongo.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * The Class QueryUniqueUser.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class QueryUniqueUser extends Query implements IQuery {

	/**
	 * Instantiates a new query unique user.
	 */
	public QueryUniqueUser(DB database) {
		super();
		executeQuery(database);
	}
	
	/* (non-Javadoc)
	 * @see mongo.query.IQuery#executeQuery()
	 */
	public void executeQuery(DB database) {
		// Only positive ID no fake members
		 DBObject conditionDbObject = new BasicDBObject(RecordsParameters.ID_MEMBER, new BasicDBObject("$gte", 0));
		 
		 DBCollection collection = (DBCollection) database.getCollection(MongoParameters.COLLECTION);
		
		 // Find the desired query
		 @SuppressWarnings("rawtypes")
		List results = collection.distinct(RecordsParameters.ID_MEMBER, conditionDbObject);
		 
		 setResult(Integer.toString(results.size()));
	}

}
