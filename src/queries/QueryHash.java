/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryHash.java
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
import mongo.query.IQuery;
import mongo.query.Query;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * The Class QueryHash.
 * 
 * @author Sawan J. Kapai Harpalani
 */
public class QueryHash extends Query implements IQuery {

	/**
	 * Instantiates a new query hash.
	 * 
	 * @param database
	 *            the database
	 */
	public QueryHash(DB database) {
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

		String map = "var map = function() {" + "var text = this.text;"
				+ "if (text) {" + "text = text.toLowerCase().split(\" \");"
				+ "for (var i = text.length - 1; i >= 0; i--) {"
				+ "if (text[i].indexOf('#') > -1)  { " + "emit(text[i], 1);"
				+ "}" + "}" + "}" + "};";

		String reduce = "var reduce = function( key, values ) {"
				+ "   var count = 0;" + "    values.forEach(function(v) {  "
				+ "count +=v; }); " + "return count }";

		collection.mapReduce(map, reduce, "count", null);
		DBCollection collectionWord = (DBCollection) database
				.getCollection("hash_count");

		DBObject groupFields = new BasicDBObject("_id", "null");

		groupFields.put("total", new BasicDBObject("$sum", "$value"));
		DBObject group = new BasicDBObject("$group", groupFields);

		@SuppressWarnings("deprecation")
		AggregationOutput output = collectionWord.aggregate(group);

		int totalHash = 0;
		for (final DBObject result : output.results()) {
			totalHash += Double.parseDouble(result.get("total").toString());
		}

		
		DBCursor cursorT = collection.find();

		setResult(Double.toString( (double) totalHash
				/ (double) cursorT.count()));
	}

}
