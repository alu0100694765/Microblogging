/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: QueryGeolocation.java
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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mongo.MongoParameters;
import mongo.RecordsParameters;
import mongo.query.IQuery;
import mongo.query.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

// TODO: Auto-generated Javadoc
/**
 * The Class QueryGeolocation.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class QueryGeolocation extends Query implements IQuery {

	/** The Constant GET_COUNTRY_GOOGLE. */
	protected final static String GET_COUNTRY_GOOGLE = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
	
	
	/**
	 * Instantiates a new query geolocation.
	 *
	 * @param database the database
	 */
	public QueryGeolocation(DB database) {
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
		Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
		dbObjIdMap.put("lngt", "$" + RecordsParameters.GEO_LNG);
		dbObjIdMap.put("lat", "$" + RecordsParameters.GEO_LAT);
		DBObject groupFields = new BasicDBObject("_id", dbObjIdMap);

		groupFields.put("count", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);

		DBObject sortFields = new BasicDBObject("count", -1);
		DBObject sort = new BasicDBObject("$sort", sortFields);
		DBObject limit = new BasicDBObject("$limit", 10);

		List<DBObject> aggregationQuery = Arrays.<DBObject>asList(group, sort, limit);
		
		Cursor output = collection.aggregate(aggregationQuery, AggregationOptions.builder().allowDiskUse(true).build());
		
		
		JSONObject geoJSON = null;
		while ( output.hasNext() ) {
	        DBObject doc = output.next();
	        try {
				geoJSON = utility.JsonReader.readJsonFromUrl(GET_COUNTRY_GOOGLE + ((DBObject)doc.get("_id")).get("lat").toString() + "," + ((DBObject)doc.get("_id")).get("lngt").toString());
	        } catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        JSONObject site = (JSONObject)(((JSONArray)geoJSON.get("results")).get(0));
	        
	        if (site.get("formatted_address").toString().contains("UK")) {
				setResult(site.get("formatted_address").toString());
				break;
			}
	    }
	}
}
