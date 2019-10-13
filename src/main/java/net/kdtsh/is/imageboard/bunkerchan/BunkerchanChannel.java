package net.kdtsh.is.imageboard.bunkerchan;

import java.util.Set;

import net.kdtsh.is.imageboard.Channel;

public class BunkerchanChannel extends Channel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -563492221184213247L;

	private static final Set<String> CHANNEL_PATH_SET = null;

	static {
//		CHANNEL_PATH_SET = Set.of("/leftypol/");
	}

	public static BunkerchanChannel getChannel(String channelPath) {
		if (CHANNEL_PATH_SET.contains(channelPath)) {
			return new BunkerchanChannel(channelPath);
		} else {
			//
			// TODO exception?
			//
			return null;
		}
	}

	public BunkerchanChannel(String channelPath) {
		this.channelPath = channelPath;
	}

	private String channelPath;

	@Override
	public String getChannelPath() {
		return channelPath;
	}
}
