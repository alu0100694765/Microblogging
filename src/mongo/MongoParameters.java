/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: MongoParameters.java
 * Version: 1.0
 * Date: 04/12/2015
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

import com.mongodb.ServerAddress;


/**
 * The Class MongoParameters.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class MongoParameters {
	
	/** The Constant HOST_NAME. */
	public final static String HOST_NAME = "localhost";
	
	/** The Constant PORT. */
	public final static int PORT = 27017;
	
	/** The Constant USERNAME. */
	public final static String USERNAME = null;
	
	/** The Constant PASSWORD. */
	public final static String PASSWORD = null;
	
	/** The Constant DATABASE. */
	public final static String DATABASE = "microblogging";
	
	/** The Constant COLLECTION. */
	public final static String COLLECTION = "records";
	
	/** The Constant ADDRESS. */
	public final static ServerAddress ADDRESS = null;
	
	// If you have a URI comment previous line and uncomment the following and declare your Host where its written HOST
//	public static InetAddress INET_ADDRESS;
//	public static ServerAddress ADDRESS;
//	public static void createAddress() {
//		try {
//			INET_ADDRESS = InetAddress.getByName("HOST") ;
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		ADDRESS = new ServerAddress(INET_ADDRESS);
//	}
	
}
