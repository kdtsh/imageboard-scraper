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
