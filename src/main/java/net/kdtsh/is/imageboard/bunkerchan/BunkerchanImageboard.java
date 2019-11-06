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
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import net.kdtsh.is.client.ImageboardClient;
import net.kdtsh.is.client.BunkerchanClientImpl;
import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.Imageboard;

public class BunkerchanImageboard extends Imageboard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4492637275536404855L;

	private Set<Channel> channelSet;

	{
		imageboardType = ImageboardType.BUNKERCHAN;
		try {
			imageboardUri = new URI("https://www.bunkerchan.xyz/");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Set<Channel> getChannels() {
		return channelSet;
	}

	@Override
	public void setChannels(Set<Channel> channelSet) {
		this.channelSet = channelSet;
	}

	@Override
	public ImageboardClient generateClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		return new BunkerchanClientImpl();
	}

}
