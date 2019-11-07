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
import java.util.Map;

import net.kdtsh.is.imageboard.bunkerchan.BunkerchanChannel;

/**
 * A class which gives the channels for each imageboard.
 * 
 * @author kdt
 *
 */
public abstract class Channels implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -261855496146297511L;

	protected Map<String, String> channelPathMap;

	/**
	 * Get a Channel.
	 * 
	 * @param channelStr the String representation of a Channel.
	 * @return a Channel corresponding to the given String.
	 * @throws NoSuchChannelException if there is no such channel on this
	 *                                imageboard.
	 */
	public Channel getChannel(String channelStr) throws NoSuchChannelException {
		if (channelPathMap.containsKey(channelStr)) {
			return new BunkerchanChannel(channelStr);
		} else {
			throw new NoSuchChannelException(
					"There is no such channel as `" + channelStr + "' on the Bunkerchan imageboard.");
		}
	}

}
