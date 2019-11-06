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

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.P;

/**
 * Child Post.
 * 
 * @author kdt
 *
 */
public class Cp extends P {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603521237035746138L;

	@JsonProperty
	private CpPost cpPost;

	private BigInteger opId;

	public CpPost getCpPost() {
		return cpPost;
	}

	public void setCpPost(CpPost cpPost) {
		this.cpPost = cpPost;
	}

	public BigInteger getOpId() {
		return opId;
	}

	public void setOpId(BigInteger opId) {
		this.opId = opId;
	}

}
