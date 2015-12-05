import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import mongo.Connection;
import mongo.InsertData;
import parse.CSVParser;
import document.Document;
import errors.FieldException;
import read.ReadFile;

/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: TestBench.java
 * Version: 1.0
 * Date: 03/12/2015
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

/**
 * @author Sawan J. Kapai Harpalani
 *
 */
public class TestBench {
	
	public static void main(String[] args) throws IOException, FieldException {
		ReadFile readFile = new ReadFile("data/test.csv");
		readFile.extractContent();
		
		ArrayList<Document> mongoContent = CSVParser.parse(readFile.getContent());
//		
//		for (Iterator iterator = mongoContent.iterator(); iterator.hasNext();) {
//			Document document = (Document) iterator.next();
//			System.out.println(document.getId() + "\t" + document.getIdMember() + "\t" + document.getTimeStamp() + "\t" + document.getText() + "\t" + document.getGeoLat() + "\t" + document.getGeoLng());
//		}
		Connection connection = Connection.getInstance();
		System.out.println("Success");
		InsertData.insertDocument(connection.getDatabase(), mongoContent);
	}
}
