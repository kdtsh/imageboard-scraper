package net.kdtsh.is.imageboard;

import java.io.Serializable;
import java.util.Set;

import org.springframework.stereotype.Component;

import net.kdtsh.is.client.Client;

@Component
public abstract class Imageboard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -206971477818674742L;
	
	public abstract Set<Channel> getChannels();
	
	public abstract void setChannels(Set<Channel> channelSet);

	public abstract Client getClient() throws Exception;

}
