/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryMessage.java
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

import java.math.BigDecimal;

import mongo.MongoParameters;
import mongo.query.IQuery;
import mongo.query.Query;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * The Class QueryMessage.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class QueryMessage extends Query implements IQuery {

	/**
	 * Instantiates a new query message.
	 *
	 * @param database the database
	 */
	public QueryMessage(DB database) {
		super();
		executeQuery(database);
	}
	
	/* (non-Javadoc)
	 * @see mongo.query.IQuery#executeQuery(com.mongodb.DB)
	 */
	public void executeQuery(DB database) {
		DBCollection collection = (DBCollection) database.getCollection("word_count");
		DBObject groupFields = new BasicDBObject( "_id", "null");
		
		groupFields.put("total",  new BasicDBObject( "$sum", "$value"));
		DBObject group = new BasicDBObject("$group", groupFields );
		
		@SuppressWarnings("deprecation")
		AggregationOutput output = collection.aggregate(group);
		
		Long totalLength = 0L;
		for (final DBObject result: output.results()) {
			BigDecimal myNumber = new BigDecimal(result.get("total").toString());
			totalLength += myNumber.longValue();
		}
		
		DBCollection collectionRecords = (DBCollection) database.getCollection(MongoParameters.COLLECTION);
		DBObject condition = new BasicDBObject( "$where", "this.text.length > 0");
		
		DBCursor totalMessages = collectionRecords.find(condition);
		
		int numMessages =  totalMessages.count();
		
		double meanLenght = (double) totalLength / (double) numMessages;
		
		setResult(Double.toString(meanLenght));
	}
}
