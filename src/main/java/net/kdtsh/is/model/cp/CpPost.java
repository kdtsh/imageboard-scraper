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
package net.kdtsh.is.model.cp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.Post;

/**
 * Child post uploads, properties, and contents.
 * 
 * @author kdt
 *
 */
public class CpPost extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1037915427277219998L;

	//
	//
	// TODO this needs to be fixed
	@JsonIgnore
	protected CpPostUploads cpPostUploads;

	@JsonProperty
	protected CpPostProperties cpPostProperties;

	public CpPostUploads getCpPostUploads() {
		return cpPostUploads;
	}

	public void setCpPostUploads(CpPostUploads cpPostUploads) {
		this.cpPostUploads = cpPostUploads;
	}

	public CpPostProperties getCpPostProperties() {
		return cpPostProperties;
	}

	public void setCpPostProperties(CpPostProperties cpPostProperties) {
		this.cpPostProperties = cpPostProperties;
	}

	public String toString() {
		return String.join(",", cpPostUploads.toString(), cpPostProperties.toString(), message.toString());
	}

}
