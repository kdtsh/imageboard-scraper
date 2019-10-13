package net.kdtsh.is.client;

import net.kdtsh.is.imageboard.Imageboard;

public class ClientFactory {

	/**
	 * Get a Client for a given Imageboard.
	 * 
	 * @param imageboard
	 * @return
	 * @throws Exception
	 */
	public static Client getClient(Imageboard imageboard) throws Exception {
		return imageboard.getClient();
	}

}
