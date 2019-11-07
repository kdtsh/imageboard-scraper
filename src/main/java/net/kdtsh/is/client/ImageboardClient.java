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
package net.kdtsh.is.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.Imageboard;
import net.kdtsh.is.imageboard.ImageboardUtils;
import net.kdtsh.is.model.Page;

/**
 * A client for retrieving posts from an imageboard.
 * 
 * @author kdt
 *
 */
public abstract class ImageboardClient {

	/**
	 * Logger for the class.
	 */
	protected Logger logger;

	/**
	 * The Imageboard with which this ImageboardClient is associated.
	 */
	protected Imageboard imageboard;

	/**
	 * Special utilities for the imageboard.
	 */
	protected ImageboardUtils imageboardUtils;

	/**
	 * HttpClient used to retrieve posts from an imageboard.
	 */
	protected HttpClient httpClient;

	/**
	 * Get the URI of a relative path on the site URI.
	 * 
	 * @param path
	 * @return
	 * @throws URISyntaxException
	 */
	public URI getURIRelativePath(String path) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(imageboard.toString());
		return uriBuilder.setPath(uriBuilder.getPath() + path).build().normalize();
	}

	/**
	 * Get a {@link net.kdtsh.is.model.Page Page} for the given channel, i.e. all
	 * the threads on the first page of the {@link net.kdtsh.is.imageboard.Channel
	 * Channel}.
	 * 
	 * @param channel the Channel for which to get the Page.
	 * @return the Page.
	 */
	public Page getPage(Channel channel) {
		try {
			Document doc = getDocumentForPath(channel.getChannelName());
			return imageboardUtils.extractPage(doc);
		} catch (URISyntaxException e) {
			logger.error(String.format("URLSyntaxException when attempting to get a Page for Bunkerchan, channel %s",
					channel.toString()), e);
		} catch (ClientProtocolException e) {
			logger.error(
					String.format("ClientProtocolException when attempting to get a Page for Bunkerchan, channel %s",
							channel.toString()),
					e);
		} catch (IOException e) {
			logger.error(String.format("IOException when attempting to get a Page for Bunkerchan, channel %s",
					channel.toString()), e);
		}
		return null;
	}

	/**
	 * Get the Document corresponding to a given URL.
	 * 
	 * @param url a {@link java.net.URL URL}
	 * @return a {@link org.jsoup.nodes.Document Document}
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Document getDocumentForPath(String path) throws URISyntaxException, ClientProtocolException, IOException {
		URI pathUri = getURIRelativePath(path);
		HttpGet httpGet = new HttpGet(pathUri);
		HttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
				|| response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
			StringWriter sw = new StringWriter();
			InputStream is = response.getEntity().getContent();
			IOUtils.copy(is, sw, Charset.defaultCharset());

			return Jsoup.parse(sw.toString());
		}
		return null;
	}

}
