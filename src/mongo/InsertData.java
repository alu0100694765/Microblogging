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

import java.util.Date;

import com.mongodb.client.MongoDatabase;

import document.Document;

/**
 * The Class InsertData.
 * 
 * @author Sawan J. Kapai Harpalani
 */
public class InsertData {


	/**
	 * Insert document.
	 * 
	 * @param database
	 *            the database
	 * @param documents
	 *            the documents
	 */
	public final static void insertDocument(MongoDatabase database,
			Document document) {
			database.getCollection(MongoParameters.COLLECTION)
					.insertOne(
							new org.bson.Document()
									.append(RecordsParameters.ID, document.getId())
									.append(RecordsParameters.ID_MEMBER, document.getIdMember())
									.append(RecordsParameters.TIMESTAMP,
											new Date(document.getTimeStamp()
													.getTime()))
									.append(RecordsParameters.TEXT, document.getText())
									.append(RecordsParameters.GEO_LAT, document.getGeoLat())
									.append(RecordsParameters.GEO_LNG, document.getGeoLng()));
	}

}

