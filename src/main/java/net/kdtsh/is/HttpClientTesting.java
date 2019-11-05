package net.kdtsh.is;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.kdtsh.is.es.ElasticsearchClient;
import net.kdtsh.is.es.ElasticsearchClientImpl;
import net.kdtsh.is.imageboard.ImageboardUtils;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanImageboard;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanUtils;
import net.kdtsh.is.model.P;
import net.kdtsh.is.model.Page;

public class HttpClientTesting {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientTesting.class);

	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException,
			KeyStoreException, KeyManagementException {

		System.setProperty("javax.net.ssl.trustStore", "/Users/kdt/development/java/web-scraper/isrgrootx1.jks");

		String url = "https://www.bunkerchan.xyz/gulag/";
		HttpClientBuilder cb = HttpClientBuilder.create();

		//
		// The following few lines ignore SSL - it'll be prudent to fix
		// this issue with affected boards (e.g. bunkerchan has B rating
		// on ssllabs.com
		//
		SSLContextBuilder sslcb = new SSLContextBuilder();
		sslcb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
		cb.setSSLContext(sslcb.build());
		HttpClient httpClient = cb.build();

		HttpGet httpGet = new HttpGet(url);

		HttpResponse response = httpClient.execute(httpGet);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
				|| response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
			logger.info("Register message sent to " + url);
			StringWriter stringWriter = new StringWriter();
			InputStream inputStream = response.getEntity().getContent();
			IOUtils.copy(inputStream, stringWriter, Charset.defaultCharset());

			String html = stringWriter.toString();

			Document doc = Jsoup.parse(html);
			
			ImageboardUtils bu = new BunkerchanUtils();
			Page page = bu.extractPage(doc);
			
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			for (P p : page.getPList()) {
				System.out.println(objectMapper.writeValueAsString(p));
			}
			
			ElasticsearchClient ec = new ElasticsearchClientImpl(httpClient);
			ec.postPs(page.getPList(), new BunkerchanImageboard());
			

		} else {
			logger.warn("Register message sent failed. Verify below information.");
			logger.warn("[URL] : " + url);
			logger.warn("[Reason] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
		}
	}

}
