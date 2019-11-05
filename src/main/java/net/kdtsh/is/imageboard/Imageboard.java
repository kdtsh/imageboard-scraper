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
