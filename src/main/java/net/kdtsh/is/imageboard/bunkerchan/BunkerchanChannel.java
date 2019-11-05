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
