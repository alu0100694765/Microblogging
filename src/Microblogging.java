import java.io.IOException;

import mongo.Connection;
import queries.QueryDates;
import queries.QueryGeolocation;
import queries.QueryHash;
import queries.QueryMessage;
import queries.QueryPorcentage;
import queries.QueryTime;
import queries.QueryUniqueUser;
import queries.QueryWordCounter;
import errors.FieldException;

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
public class Microblogging {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FieldException the field exception
	 */
	public static void main(String[] args) throws IOException, FieldException {
		// Only for inserting purposes
		//ReadFile readFile = new ReadFile("data/microblogDataset_COMP6235_CW2.csv");
		//ReadFile readFile = new ReadFile("data/test.csv");
		//readFile.extractContent();
		
		Connection connection = Connection.getInstance();
		System.out.println("Connected to Mongo! :)");
		
		// Only for inserting purposes
		//CSVParser.parse(readFile.getContent(), connection);
		
		// Queries time
//		QueryUniqueUser uniqueUser = new QueryUniqueUser(connection.getDatabase());
//		System.out.println("Unique users --> " + uniqueUser.getResult());
//		
//		QueryPorcentage porcentage = new QueryPorcentage(connection.getDatabase());
//		System.out.println("Percentage --> " + porcentage.getResult());
//		
//		QueryDates dates = new QueryDates(connection.getDatabase());
//		System.out.println(dates.getResult());
//		
//		QueryWordCounter counter = new QueryWordCounter(connection.getDatabase());
//		System.out.println("Common unigram --> " + counter.getResult());
		
		QueryHash hash = new QueryHash(connection.getDatabase());
		System.out.println("Average number of # --> " + hash.getResult());
		
//		QueryGeolocation geo = new QueryGeolocation(connection.getDatabase());
//		System.out.println("Area of UK --> " + geo.getResult());
//		
//		QueryMessage message = new QueryMessage(connection.getDatabase());
//		System.out.println("Mean of the lenght of a message --> " + message.getResult());
//		
//		QueryTime time = new QueryTime(connection.getDatabase());
//		System.out.println("mean time delta between all messages --> " + time.getResult());
	}
}
