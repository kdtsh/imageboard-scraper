package net.kdtsh.is;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.kdtsh.is.imageboard.bunkerchan.BunkerchanUtils;
import net.kdtsh.is.model.op.Op;

public class HttpClientTesting {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientTesting.class);

	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException,
			KeyStoreException, KeyManagementException {

		System.setProperty("javax.net.ssl.trustStore", "/Users/kdt/development/java/web-scraper/isrgrootx1.jks");

		String url = "https://www.bunkerchan.xyz/leftypol/";
//		String url = "https://www.4chan.org/b/";
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
			
			BunkerchanUtils bu = new BunkerchanUtils();
			List<Op> opList = bu.extractOpList(doc);
			for (Op op: opList) {
				System.out.println(op.toString());
			}
			
			
			
			
//			Elements elements = doc.getElementsByClass("opCell");
//
//			int count = 1;
//			for (Element element : elements) {
//				logger.info("Body HTML element " + count + ": " + element.text());
//
////				Elements childElements = element.children();
//				Elements innerOPElements = element.getElementsByClass("postCell");
//				for (Element childElement : innerOPElements) {
//					logger.info("Child HTML element tag: " + childElement.tagName());
//					logger.info("Child HTML element class: " + childElement.className());
//
//					String id = childElement.id();
//					String dataBoardUri = childElement.attr("data-boarduri");
//
//					Elements createds = childElement.getElementsByClass("labelCreated");
//					for (Element created : createds) {
//						logger.info("Child HTTP element created: " + created.text());
//
//						Instant createdInst = ZonedDateTime
//								.of(LocalDateTime.parse(created.text(), BunkerchanUtils.BUNKERCHAN_DATE_TIME_FORMATTER),
//										ZoneId.systemDefault())
//								.toInstant();
//						logger.info("Child HTTP element created: " + createdInst.getEpochSecond());
//
//					}
//
//					// <a class="linkName " href="mailto:sage">Anonymous</a>
//					Elements namesAndEmails = childElement.getElementsByClass("linkName");
//					for (Element nameAndEmail : namesAndEmails) {
//						logger.info("Child HTTP element email: " + nameAndEmail.attr("href"));
//						logger.info("Child HTTP element name: " + nameAndEmail.text());
//					}
//
//					logger.info("Child HTML element ID: " + id);
//					logger.info("Child HTML element databoarduri: " + dataBoardUri);
//					Elements messageElements = childElement.getElementsByClass("divMessage");
//					for (Element messageElement : messageElements) {
//						logger.info("Message HTML element " + count + ": " + messageElement.text() + "\n");
//					}
//				}
//
//				System.out.println("\n\n\n\n\n\n\n");
//				count++;
//			}

		} else {
			logger.warn("Register message sent failed. Verify below information.");
			logger.warn("[URL] : " + url);
			logger.warn("[Reason] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
		}
	}

}
