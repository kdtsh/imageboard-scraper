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
package net.kdtsh.is.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.kdtsh.is.model.P;
import net.kdtsh.is.model.Page;

public class ElasticsearchClientImpl implements ElasticsearchClient {

	private static final Logger logger = LoggerFactory.getLogger(ElasticsearchClientImpl.class);

	private static final String URL_FORMAT = "http://${HOST}/${IMAGEBOARD_TYPE}/_doc/${POST_ID}";

	/**
	 * The location of the elasticsearch instance. Defined by the JVM argument
	 * <code>es.loc</code>. Should be in the form &lt;ip/host&gt;:&lt;port&gt; -
	 * e.g. <code>elasticsearch.mydomain.com:9200</code>.
	 * <p>
	 * 
	 * This defaults to <code>localhost:9200</code> if no other input is given.
	 */
	private static final String ES_LOC;

	static {
		String esLoc = System.getProperty("es.loc");
		if (esLoc == null) {
			esLoc = "localhost:9200";
		}
		ES_LOC = esLoc;
	}

	/**
	 * HttpClient.
	 */
	private HttpClient httpClient;

	/**
	 * Implementation of ElasticsearchClient with a simple HttpClient which ignores
	 * any SSL.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 */
	public ElasticsearchClientImpl() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		HttpClientBuilder cb = HttpClientBuilder.create();
		SSLContextBuilder sslcb = new SSLContextBuilder();
		sslcb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
		cb.setSSLContext(sslcb.build());
		this.httpClient = cb.build();
	}

	/**
	 * Create a new ElasticsearchClientImpl given an HttpClient.
	 * 
	 * @param httpClient the HttpClient to associated with this ElasticsearchClient.
	 */
	public ElasticsearchClientImpl(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public void postPage(Page page) throws ClientProtocolException, IOException {

		for (P p : page.getPList()) {
			StringEntity stringEntity = new StringEntity(p.toJson(), "UTF-8");

			Map<String, String> valuesMap = new HashMap<>();
			valuesMap.put("HOST", ES_LOC);
			valuesMap.put("IMAGEBOARD_TYPE", page.getImageboard().getImageboardType().toString());
			valuesMap.put("POST_ID", p.getBoardName() + "-" + p.getId().toString());

			StringSubstitutor sub = new StringSubstitutor(valuesMap);
			String url = sub.replace(URL_FORMAT);

			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
			httpPost.setEntity(stringEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			httpPost.reset();
			BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));

			logger.info("Response:");
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				logger.info(line);
			}

		}
	}

}
