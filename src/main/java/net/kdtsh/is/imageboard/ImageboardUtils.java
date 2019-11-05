package net.kdtsh.is.imageboard;

import java.net.URL;

import org.jsoup.nodes.Document;

import net.kdtsh.is.model.Page;

/**
 * Static methods for handling a particular imageboard.
 * 
 * @author kdt
 *
 */
public abstract class ImageboardUtils {

	/**
	 * Get the URL for a channel.
	 * 
	 * @param imageboard
	 * @param channel
	 * @return
	 */
	public abstract URL getChannelUrl(Channel channel);

	/**
	 * Extract a `Page' given a Document. This involves traversing a page, following
	 * relevant links to threads, and creating a full view of all the threads on a
	 * given page on a given imageboard (this will probably be the first page of the
	 * board).
	 * 
	 * @param doc
	 * @param imageboard
	 * @return
	 */
	public abstract Page extractPage(Document doc);

}
