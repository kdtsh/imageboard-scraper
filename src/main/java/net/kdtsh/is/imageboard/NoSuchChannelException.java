package net.kdtsh.is.imageboard;

/**
 * A RuntimeException thrown when there is no such channel for a given board.
 * 
 * @author kdt
 *
 */
public class NoSuchChannelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1425225903141283820L;

	public NoSuchChannelException(String s) {
		super(s);
	}

}
