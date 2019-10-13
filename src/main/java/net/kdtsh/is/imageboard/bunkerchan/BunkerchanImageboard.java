package net.kdtsh.is.imageboard.bunkerchan;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import org.springframework.stereotype.Component;

import net.kdtsh.is.client.BunkerchanClientImpl;
import net.kdtsh.is.client.Client;
import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.Imageboard;

@Component
public class BunkerchanImageboard extends Imageboard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4492637275536404855L;

	private Set<Channel> channelSet;

	@Override
	public Set<Channel> getChannels() {
		return channelSet;
	}

	@Override
	public void setChannels(Set<Channel> channelSet) {
		this.channelSet = channelSet;
	}

	@Override
	public Client getClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		return BunkerchanClientImpl.getClient();
	}

}
