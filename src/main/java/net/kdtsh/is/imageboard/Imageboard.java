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

import java.io.Serializable;
import java.net.URI;

import net.kdtsh.is.client.ImageboardClient;

/**
 * Abstract top-level class for an imageboard.
 * 
 * @author kdt
 *
 */
public abstract class Imageboard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -206971477818674742L;

	/**
	 * The type of imageboard, i.e. what imageboard it is.
	 */
	protected ImageboardType imageboardType;

	/**
	 * The site associated with the imageboard.
	 */
	protected URI uri;

	/**
	 * The Channels associated with the imageboard.
	 */
	protected Channels channels;

	/**
	 * The ImageboardClient associated with the imageboard.
	 */
	protected ImageboardClient imageboardClient;

	/**
	 * Get the type of imageboard.
	 * 
	 * @return
	 */
	public ImageboardType getImageboardType() {
		return imageboardType;
	}

	/**
	 * Get the Channels of the imageboard.
	 * 
	 * @return
	 */
	public Channels getChannels() {
		return channels;
	}

	/**
	 * Get the ImageboardClient of the imageboard.
	 * 
	 * @return
	 */
	public ImageboardClient getImageboardClient() {
		return imageboardClient;
	}

}
