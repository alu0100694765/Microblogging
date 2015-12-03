/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: WriteFile.java
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Class WriteFile.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class WriteFile {
	
	/** The file name. */
	protected String fileName;
	
	/** The writing buffer. */
	protected BufferedWriter writingBuffer;
	
	/** The file writer. */
	protected FileWriter fileWriter;
	
	/** The output file. */
	protected File outputFile;
	
	/**
	 * Instantiates a new write file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public WriteFile(String file) throws IOException {
		fileName = file;
		outputFile = new File(fileName);
		fileWriter = new FileWriter(outputFile.getAbsoluteFile());
		writingBuffer = new BufferedWriter(fileWriter);
		
		// If the file does not exist then create one
		fileExist();
	}
	
	/**
	 * Write content.
	 *
	 * @param content the content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeContent(ArrayList<String> content) throws IOException {
		for (Iterator<String> content_iterator = content.iterator(); content_iterator.hasNext();) {
			String content_line = (String) content_iterator.next();
			
			writingBuffer.write(content_line);
			writingBuffer.newLine();
		}
		
		writingBuffer.close();
	}
	
	/**
	 * File exist.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void fileExist() throws IOException {
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
	}
}
