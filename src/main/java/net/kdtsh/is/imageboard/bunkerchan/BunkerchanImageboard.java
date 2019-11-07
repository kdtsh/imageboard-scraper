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
package net.kdtsh.is.imageboard.bunkerchan;

import java.net.URI;

import net.kdtsh.is.client.BunkerchanImageboardClient;
import net.kdtsh.is.client.ImageboardClient;
import net.kdtsh.is.imageboard.Channels;
import net.kdtsh.is.imageboard.Imageboard;
import net.kdtsh.is.imageboard.ImageboardType;

/**
 * An imageboard for Bunkerchan.
 * 
 * @author kdt
 *
 */
public class BunkerchanImageboard extends Imageboard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4492637275536404855L;

	{
		imageboardType = ImageboardType.BUNKERCHAN;
		uri = ImageboardType.BUNKERCHAN.getImageboardUrl();
		channels = new BunkerchanChannels();
		imageboardClient = new BunkerchanImageboardClient(this);
	}

	public BunkerchanImageboard(ImageboardClient imageboardClient) {
		imageboardType = ImageboardType.BUNKERCHAN;
		uri = ImageboardType.BUNKERCHAN.getImageboardUrl();
		channels = new BunkerchanChannels();
		this.imageboardClient = imageboardClient;
	}

	/**
	 * Constructor.
	 * 
	 * @param imageboardType
	 * @param uri
	 * @param channels
	 * @param imageboardClient
	 */
	public BunkerchanImageboard(ImageboardType imageboardType, URI uri, Channels channels,
			ImageboardClient imageboardClient) {
		this.imageboardType = imageboardType;
		this.uri = uri;
		this.channels = channels;
		this.imageboardClient = imageboardClient;
	}

}
