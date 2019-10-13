package net.kdtsh.is.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.ImageboardUtils;
import net.kdtsh.is.model.P;
import net.kdtsh.is.model.op.Op;

public class BunkerchanClientImpl implements Client {

	private HttpClient httpClient;

	public BunkerchanClientImpl(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Get a Client which has an HttpClient that `ignores' SSL.
	 * 
	 * @param imageboard
	 * @param channel
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public static BunkerchanClientImpl getClient()
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

		HttpClientBuilder cb = HttpClientBuilder.create();
		SSLContextBuilder sslcb = new SSLContextBuilder();
		sslcb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
		cb.setSSLContext(sslcb.build());
		HttpClient httpClient = cb.build();
		return new BunkerchanClientImpl(httpClient);

	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public List<P> getPList(Channel channel) {
		return null;
	}

	public List<P> getPList(Channel channel, Instant sinceInstant) {
		return null;
	}

	public List<Op> getOpList(Channel channel) {

		List<Op> opList = null;
		URL imageboardUrl = ImageboardUtils.getImageboardUrl(null);

		try {
			HttpGet httpGet = new HttpGet(imageboardUrl.toURI());
			HttpResponse response = httpClient.execute(httpGet);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
					|| response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
				StringWriter sw = new StringWriter();
				InputStream is = response.getEntity().getContent();
				IOUtils.copy(is, sw, Charset.defaultCharset());

				Document doc = Jsoup.parse(sw.toString());
				opList = ImageboardUtils.extractOpList(doc, null);
			}
		} catch (URISyntaxException e) {

			//
			// TODO handle exception
			//
			e.printStackTrace();
			return opList;
		} catch (ClientProtocolException e) {

			//
			// TODO handle exception
			//
			e.printStackTrace();
			return opList;
		} catch (IOException e) {

			//
			// TODO handle exception
			//
			e.printStackTrace();
			return opList;
		}

		return opList;

	}

	public List<Op> getOpList(Channel channel, Instant sinceInstant) {
		return null;
	}

}
