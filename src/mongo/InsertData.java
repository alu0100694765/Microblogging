/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: InsertData.java
 * Version: 1.0
 * Date: 05/12/2015
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
import java.util.Date;
import java.util.Iterator;

import com.mongodb.client.MongoDatabase;

import document.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class InsertData.
 * 
 * @author Sawan J. Kapai Harpalani
 */
public class InsertData {

	/** The Constant ID. */
	protected final static String ID = "_id";

	/** The Constant ID_MEMBER. */
	protected final static String ID_MEMBER = "id_member";

	/** The Constant TIMESTAMP. */
	protected final static String TIMESTAMP = "timestamp";

	/** The Constant TEXT. */
	protected final static String TEXT = "text";

	/** The Constant GEO_LAT. */
	protected final static String GEO_LAT = "geo_lat";

	/** The Constant GEO_LNG. */
	protected final static String GEO_LNG = "geo_lng";

	/**
	 * Insert document.
	 * 
	 * @param database
	 *            the database
	 * @param documents
	 *            the documents
	 */
	public final static void insertDocument(MongoDatabase database, ArrayList<Document> documents) {

		for (Iterator<Document> iterator = documents.iterator(); iterator
				.hasNext();) {
			Document document = (Document) iterator.next();

			database.getCollection(MongoParameters.COLLECTION).insertOne(
					new org.bson.Document().append(ID, document.getId())
							.append(ID_MEMBER, document.getIdMember())
							.append(TIMESTAMP, new Date(document.getTimeStamp().getTime()))
							.append(TEXT, document.getText())
							.append(GEO_LAT, document.getGeoLat())
							.append(GEO_LNG, document.getGeoLng()));
			
		}

	}
}
