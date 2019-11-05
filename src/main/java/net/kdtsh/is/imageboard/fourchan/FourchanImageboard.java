package net.kdtsh.is.imageboard.fourchan;

import java.util.Set;

import net.kdtsh.is.client.ImageboardClient;
import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.Imageboard;
import net.kdtsh.is.imageboard.Imageboard.ImageboardType;

public class FourchanImageboard extends Imageboard {

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
