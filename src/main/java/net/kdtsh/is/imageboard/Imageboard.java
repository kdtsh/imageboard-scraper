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
import java.net.URISyntaxException;
import java.util.Set;

import org.apache.http.client.utils.URIBuilder;

import net.kdtsh.is.client.ImageboardClient;

public abstract class Imageboard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -206971477818674742L;

	protected ImageboardType imageboardType;

	protected URI imageboardUri;

	public ImageboardType getImageboardType() {
		return imageboardType;
	}

	public URI getImageboardUri() {
		return imageboardUri;
	}

	public URI getURIRelativePath(String path) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(imageboardUri.toString());
		return uriBuilder.setPath(uriBuilder.getPath() + path).build().normalize();
	}

	public abstract Set<Channel> getChannels();

	public abstract void setChannels(Set<Channel> channelSet);

	public abstract ImageboardClient generateClient() throws Exception;

	public static enum ImageboardType {
		BUNKERCHAN, FOURCHAN;

		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
	}

}
