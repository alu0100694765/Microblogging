/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryDates.java
 * Version: 1.0
 * Date: 11/12/2015
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
import mongo.query.IQuery;
import mongo.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author Sawan J. Kapai Harpalani
 *
 */
public class QueryDates extends Query implements IQuery {

	/**
	 * Instantiates a new query dates.
	 *
	 * @param database the database
	 */
	public QueryDates(DB database) {
		super();
		executeQuery(database);
	}
	
	/* (non-Javadoc)
	 * @see mongo.query.IQuery#executeQuery(com.mongodb.DB)
	 */
	public void executeQuery(DB database) {
		DBCollection collection = (DBCollection) database.getCollection(MongoParameters.COLLECTION);

		DBObject earliest = new BasicDBObject("timestamp", 1);
		DBObject latest = new BasicDBObject("timestamp", -1);
		
		DBCursor cursor = collection.find();
		cursor.sort(earliest).limit(1);
		
		List<DBObject> results = cursor.toArray();
		
		setResult("Earliest --> " + results.get(0).get("timestamp").toString() + "\nLatest --> ");
		
		cursor = collection.find();
		cursor.sort(latest).limit(1);
		
		results = cursor.toArray();
		
		setResult(getResult() + results.get(0).get("timestamp").toString());
	}

}
