/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryTime.java
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

import java.util.Date;
import java.util.Iterator;

import mongo.MongoParameters;
import mongo.RecordsParameters;
import mongo.query.IQuery;
import mongo.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * The Class QueryTime.
 * 
 * @author Sawan J. Kapai Harpalani
 */
public class QueryTime extends Query implements IQuery {

	/**
	 * Instantiates a new query time.
	 * 
	 * @param database
	 *            the database
	 */
	public QueryTime(DB database) {
		super();
		executeQuery(database);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mongo.query.IQuery#executeQuery(com.mongodb.DB)
	 */
	public void executeQuery(DB database) {
		DBCollection collection = (DBCollection) database
				.getCollection(MongoParameters.COLLECTION);

		collection.createIndex(new BasicDBObject(RecordsParameters.TIMESTAMP, 1));
		
		BasicDBObject fields = new BasicDBObject();
		fields.put(RecordsParameters.ID, 0);
		fields.put(RecordsParameters.ID_MEMBER, 0);
		fields.put(RecordsParameters.TEXT, 0);
		fields.put(RecordsParameters.GEO_LAT, 0);
		fields.put(RecordsParameters.GEO_LNG, 0);

		DBObject sort = new BasicDBObject(RecordsParameters.TIMESTAMP, -1);

		DBCursor timeStamps = collection.find(new BasicDBObject(), fields)
				.sort(sort);

		long substractionTimes = 0;
		long totalSubstraction = 0;
		Date previousDate = null;
		Date currDate = null;
		int it = 0;
		for (Iterator<DBObject> iterator = timeStamps.iterator(); iterator.hasNext();) {
			DBObject dbObject = (DBObject) iterator.next();
			if (it == 0) {
				previousDate = (Date) dbObject.get(RecordsParameters.TIMESTAMP);
			} else {
				currDate = (Date) dbObject.get(RecordsParameters.TIMESTAMP);
				substractionTimes = previousDate.getTime() - currDate.getTime();
				totalSubstraction = totalSubstraction + substractionTimes;
				previousDate = currDate;
			}
			it++;
		}
		
		double meanMessages = (double) totalSubstraction / (double) timeStamps.count();
		
		setResult(Double.toString(meanMessages));
	}

}
