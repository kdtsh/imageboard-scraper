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
