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

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.PostProperties;

/**
 * Original post properties.
 * 
 * @author kdt
 *
 */
public class OpPostProperties extends PostProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014078241191825447L;

	@JsonProperty
	private String postSubject;

	@JsonProperty
	private String threadLink;

	public String getPostSubject() {
		return postSubject;
	}

	public void setPostSubject(String postSubject) {
		this.postSubject = postSubject;
	}

	public String getThreadLink() {
		return threadLink;
	}

	public void setThreadLink(String threadLink) {
		this.threadLink = threadLink;
	}

	public String toString() {
		return String.join(",", postSubject, threadLink, name, email, created.toString());
	}

}
