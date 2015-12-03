/**
 * Author: Sawan J. Kapai Harpalani
 * Email: sawankapai@gmail.com
 * File name: Document.java
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
package document;

import java.sql.Timestamp;

/**
 * The Class Document.
 *
 * @author Sawan J. Kapai Harpalani
 */
public class Document {
	
	/** The id. */
	private Integer id;
	
	/** The id member. */
	private Integer idMember;
	
	/** The time stamp. */
	private Timestamp timeStamp;
	
	/** The text. */
	private String text;
	
	/** The geo lat. */
	private Double geoLat;
	
	/** The geo lng. */
	private Double geoLng;

	/**
	 * Instantiates a new document.
	 *
	 * @param id the id
	 * @param idMember the id member
	 * @param timeStamp the time stamp
	 * @param text the text
	 * @param geoLat the geo lat
	 * @param geoLng the geo lng
	 */
	public Document(Integer id, Integer idMember, Timestamp timeStamp, String text, Double geoLat, Double geoLng) {
		this.id = id;
		this.idMember = idMember;
		this.timeStamp = timeStamp;
		this.text = text;
		this.geoLat = geoLat;
		this.geoLng = geoLng;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the id member.
	 *
	 * @return the idMember
	 */
	public Integer getIdMember() {
		return idMember;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the geo lat.
	 *
	 * @return the geoLat
	 */
	public Double getGeoLat() {
		return geoLat;
	}

	/**
	 * Gets the geo lng.
	 *
	 * @return the geoLng
	 */
	public Double getGeoLng() {
		return geoLng;
	}
	
	
}
