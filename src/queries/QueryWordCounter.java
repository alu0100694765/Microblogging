/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryWordCounter.java
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

import java.util.Iterator;
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
public class QueryWordCounter extends Query implements IQuery {

	public QueryWordCounter(DB database) {
		super();
		executeQuery(database);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mongo.query.IQuery#executeQuery(com.mongodb.DB)
	 */
	public void executeQuery(DB database) {
		DBCollection collection = (DBCollection) database.getCollection(MongoParameters.COLLECTION);
		
		String map = "var map = function() {"  + "var text = this.text;" + "if (text) {" + 
        "text = text.toLowerCase().split(\" \");" + 
        "for (var i = text.length - 1; i >= 0; i--) {" +
            "if (text[i])  { " +     
               "emit(text[i], 1);" + 
            "}" +
        "}" +
    "}" +
"};";
	 
		String reduce = "var reduce = function( key, values ) {"
				+ "   var count = 0;"
				+ "    values.forEach(function(v) {  "
				+ "count +=v; }); "
				+ "return count }";
		
		collection.mapReduce(map, reduce, "word", null);
		DBCollection collectionWord = (DBCollection) database.getCollection("word_count");
		
		DBObject top = new BasicDBObject("value", -1);
		DBCursor cursor = collectionWord.find().sort(top).limit(10);
		
		List<DBObject> results = cursor.toArray();
		String finalR = "";
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = results.iterator(); iterator.hasNext();) {
			DBObject dbObject = (DBObject) iterator.next();
			finalR = finalR + dbObject.get("_id").toString() + " --> " + dbObject.get("value").toString() + "\n";
		}
		setResult(finalR);
	}
}
