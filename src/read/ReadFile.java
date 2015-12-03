/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: ReadFile.java
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
package read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class ReadFile.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class ReadFile {
	
	/** The reading buffer. */
	protected BufferedReader readingBuffer;
	
	/** The file name. */
	protected String fileName;
	
	/** The content. */
	private ArrayList<String> content;
	
	
	/**
	 * Instantiates a new read file.
	 *
	 * @param file the file
	 */
	public ReadFile(String file) {
		fileName = file;
		content = new ArrayList<String>();
	}
	
	/**
	 * Extract content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void extractContent() throws IOException {
		readingBuffer = new BufferedReader(new FileReader(fileName));
		
		String fileLine;
		
		while ((fileLine = readingBuffer.readLine()) != null) {
			content.add(fileLine);
		}
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public ArrayList<String> getContent() {
		return content;
	}
	
	
}
