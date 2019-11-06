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
package net.kdtsh.is.imageboard;

import java.io.Serializable;

/**
 * A channel, or board, of an imageboard.
 * 
 * @author kdt
 *
 */
public abstract class Channel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3513725384809500811L;

	protected String channelPath;

	public String getChannelPath() {
		return channelPath;
	}	
}
