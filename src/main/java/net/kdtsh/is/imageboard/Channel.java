package net.kdtsh.is.imageboard;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * A channel, or board, of an imageboard.
 * 
 * @author kdt
 *
 */
@Component
public abstract class Channel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3513725384809500811L;

	public abstract String getChannelPath();

}
