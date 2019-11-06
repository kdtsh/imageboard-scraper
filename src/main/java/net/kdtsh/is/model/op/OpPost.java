/**
 * Copyright (C) 2019 Kenan Toker
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.kdtsh.is.model.op;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kdtsh.is.model.Post;

/**
 * Original post uploads, properties, and contents.
 * 
 * @author kdt
 *
 */
public class OpPost extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6171562940377448035L;

	//
	//
	// TODO this needs to be fixed
	@JsonIgnore
	private OpPostUploads opPostUploads;

	@JsonProperty
	private OpPostProperties opProperties;

	public OpPostUploads getOpPostUploads() {
		return opPostUploads;
	}

	public void setOpPostUploads(OpPostUploads opPostUploads) {
		this.opPostUploads = opPostUploads;
	}

	public OpPostProperties getOpProperties() {
		return opProperties;
	}

	public void setOpProperties(OpPostProperties opProperties) {
		this.opProperties = opProperties;
	}

	public String toString() {
		return String.join(",", opPostUploads.toString(), opProperties.toString(), message);
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		OpPostProperties opp = new OpPostProperties();
		opp.setCreated(Instant.now());
		opp.setEmail("kenan@kdtsh.net");
		opp.setName("Anonymous");
		opp.setPostSubject("Some subject.");
		opp.setThreadLink("/leftypol/12345.html");
		
		OpPost opPost = new OpPost();
		opPost.setMessage("Soem message.");
		opPost.setOpPostUploads(null);
		opPost.setOpProperties(opp);
		
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(opPost));
		
	}

}
