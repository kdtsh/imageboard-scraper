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
package net.kdtsh.is.imageboard.fourchan;

import java.util.Set;

import net.kdtsh.is.client.ImageboardClient;
import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.Imageboard;

public class FourchanImageboard extends Imageboard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7585528683415688440L;

	{
		imageboardType = ImageboardType.FOURCHAN;
	}

	@Override
	public Set<Channel> getChannels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setChannels(Set<Channel> channelSet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImageboardClient generateClient() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
