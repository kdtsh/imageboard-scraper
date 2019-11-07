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

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.P;

/**
 * Original post.
 * 
 * @author kdt
 *
 */
public class Op extends P {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365622628456384974L;

	@JsonProperty
	private OpPost opPost;

	@JsonProperty
	private List<BigInteger> cpIdList;

	public OpPost getOpPost() {
		return opPost;
	}

	public void setOpPost(OpPost opPost) {
		this.opPost = opPost;
	}

	public List<BigInteger> getCpIdList() {
		return cpIdList;
	}

	public void setCpIdList(List<BigInteger> cpList) {
		this.cpIdList = cpList;
	}

}
