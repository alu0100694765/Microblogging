/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryPorcentage.java
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

import mongo.MongoParameters;
import mongo.RecordsParameters;
import mongo.query.IQuery;
import mongo.query.Query;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * The Class QueryPorcentage.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class QueryPorcentage extends Query implements IQuery {

	/**
	 * Instantiates a new query porcentage.
	 *
	 * @param database the database
	 */
	public QueryPorcentage(DB database) {
		super();
		executeQuery(database);
	}
	
	/* (non-Javadoc)
	 * @see mongo.query.IQuery#executeQuery(com.mongodb.DB)
	 */
	public void executeQuery(DB database) {
		DBCollection collection = (DBCollection) database.getCollection(MongoParameters.COLLECTION);
		DBObject groupFields = new BasicDBObject( "_id", "$"+RecordsParameters.ID_MEMBER);
		
		groupFields.put("count",  new BasicDBObject( "$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields );
		
		DBObject sortFields = new BasicDBObject("count", -1);
		DBObject sort = new BasicDBObject("$sort", sortFields );
		DBObject limit = new BasicDBObject("$limit", 10);
		
		@SuppressWarnings("deprecation")
		AggregationOutput output = collection.aggregate(group, sort, limit);
		
		 int numTweetsTopTen = 0;
		 for (final DBObject result: output.results()) {
			 Long currentID = Long.parseLong(result.get("_id").toString());
			 Integer currentNum = Integer.parseInt(result.get("count").toString());
			 if ( currentID > 0) {
				numTweetsTopTen += currentNum;
			}
		 }
		 
		 DBObject conditionDbObject = new BasicDBObject(RecordsParameters.ID_MEMBER, new BasicDBObject("$gte", 0));
		 
		 DBCursor totalTweets = collection.find(conditionDbObject);
		 
		 float percentage = (100 / (float) totalTweets.count()) * (float) numTweetsTopTen;
		 
		 setResult(Float.toString(percentage));
	}

}
