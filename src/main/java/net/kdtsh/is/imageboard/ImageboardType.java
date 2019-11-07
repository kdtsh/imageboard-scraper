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

import java.net.URI;
import java.net.URISyntaxException;

/**
 * The allowed imageboards.
 * <p>
 * 
 * Each imageboard is identified by a name, and has associated with it a URL
 * represented as a URI.
 * 
 * @author kdt
 *
 */
public enum ImageboardType {
	BUNKERCHAN("https://www.bunkerchan.xyz/"), FOURCHAN("https://www.4chan.org/");

	/**
	 * The imageboard's URL represented as a URI.
	 */
	private URI imageboardUrl;

	private ImageboardType(String imageboardUrlStr) {
		try {
			this.imageboardUrl = new URI(imageboardUrlStr);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get the imageboard's URL.
	 * 
	 * @return a URI representing the imageboard's URL.
	 */
	public URI getImageboardUrl() {
		return imageboardUrl;
	}

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
