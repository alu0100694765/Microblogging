/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: JsonReader.java
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
package utility;

/**
 * @author Sawan J. Kapai Harpalani
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
}