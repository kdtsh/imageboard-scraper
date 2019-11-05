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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.model.Page;

/**
 * A client for retrieving posts from an imageboard.
 * 
 * @author kdt
 *
 */
public abstract class ImageboardClient {

	protected HttpClient httpClient;

	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * Get a {@link net.kdtsh.is.model.Page Page} for the given channel, i.e. all
	 * the threads on the first page of the {@link net.kdtsh.is.imageboard.Channel
	 * Channel}.
	 * 
	 * @param channel the Channel for which to get the Page.
	 * @return the Page.
	 */
	public abstract Page getPage(Channel channel);

	/**
	 * Get the Document corresponding to a given URL.
	 * 
	 * @param url a {@link java.net.URL URL}
	 * @return a {@link org.jsoup.nodes.Document Document}
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Document getDocument(URI uri) throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(uri);
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
