/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: CSVParser.java
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
package parse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import document.Document;
import errors.FieldException;

/**
 * @author Sawan J. Kapai Harpalani
 * 
 */
public class CSVParser {

	protected final static String CSV_TOKEN = ",";
	protected final static int ID_FIELD = 0;
	protected final static int ID_MEMBER_FIELD = 1;
	protected final static int TIMESTAMP_FIELD = 2;
	protected final static int TEXT_FIELD = 3;
	protected final static int GEO_LAT_FIELD = 4;
	protected final static int GEO_LNG_FIELD = 5;

	public static ArrayList<Document> parse(ArrayList<String> content) throws FieldException {
		ArrayList<Document> parsedContent =  new ArrayList<Document>();
		
		for (Iterator iterator = content.iterator(); iterator.hasNext();) {
			String contentLine = (String) iterator.next();
			
			StringTokenizer tokenizer = new StringTokenizer(CSV_TOKEN);
			
			int currentField = 0;
			
			// Document values
			Long id = 0L;
			Long idMember = 0L;
			Timestamp timestamp = null;
			String text = null;
			Double geoLat = 0D;
			Double geoLng = 0D;
			
			while (tokenizer.hasMoreTokens()) {
				String field = tokenizer.nextToken();
				
				switch (currentField) {
					case ID_FIELD:
						id = Long.parseLong(field);
						break;
					
					case ID_MEMBER_FIELD:
						idMember = Long.parseLong(field);
						break;
					
					case TIMESTAMP_FIELD:
						timestamp = Timestamp.valueOf(field);
						break;
					
					case TEXT_FIELD:
						text = field;
						break;
						
					case GEO_LAT_FIELD:
						geoLat = Double.parseDouble(field);
						break;
						
					case GEO_LNG_FIELD:
						geoLng = Double.parseDouble(field);
						break;
						
					default:
						throw new FieldException();
				}
				
			}
		}
		
		return parsedContent;
	}
	

}
