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
package net.kdtsh.is.imageboard.bunkerchan;

import java.util.HashMap;
import java.util.Map;

import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.NoSuchChannelException;

public class BunkerchanChannel extends Channel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -563492221184213247L;

	private static Map<String, String> CHANNEL_PATH_MAP = null;

	static {
		String[] channels = { "leftypol", "gulag" };
		CHANNEL_PATH_MAP = new HashMap<>();
		for (String channel : channels) {
			CHANNEL_PATH_MAP.put(channel, channel);
		}
	}

	public static Channel checkChannel(String channel) {
		if (CHANNEL_PATH_MAP.containsKey(channel)) {
			return new BunkerchanChannel(channel);
		} else {
			throw new NoSuchChannelException(
					"There is no such channel as `" + channel + "' on the Bunkerchan imageboard.");
		}
	}

	public BunkerchanChannel(String channelPath) {
		this.channelPath = channelPath;
	}

}
