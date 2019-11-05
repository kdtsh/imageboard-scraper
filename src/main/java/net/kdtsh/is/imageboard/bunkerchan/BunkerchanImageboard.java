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
