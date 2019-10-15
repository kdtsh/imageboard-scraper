package net.kdtsh.is.imageboard;

import java.net.URL;

import org.jsoup.nodes.Document;

import net.kdtsh.is.model.Page;

public abstract class ImageboardUtils {

	/**
	 * TODO implement this method
	 * @param imageboard
	 * @param channel
	 * @return
	 */
	public abstract URL getImageboardUrl();
	
	/**
	 * TODO implement this method
	 * @param doc
	 * @param imageboard
	 * @return
	 */
	public abstract Page extractPage(Document doc);

}
