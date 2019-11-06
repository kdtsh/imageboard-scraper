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
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.kdtsh.is.imageboard.Channel;
import net.kdtsh.is.imageboard.ImageboardUtils;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanImageboard;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanUtils;
import net.kdtsh.is.model.Page;

public class BunkerchanClientImpl extends ImageboardClient {

	private static final Logger logger = LoggerFactory.getLogger(BunkerchanClientImpl.class);

	private static final BunkerchanImageboard BUNKERCHAN_IMAGEBOARD = new BunkerchanImageboard();

	private ImageboardUtils imageboardUtils = new BunkerchanUtils();

	/**
	 * No-arg constructor. Generate a standard HttpClient, i.e. one which `ignores'
	 * SSL.
	 * 
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	public BunkerchanClientImpl() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		HttpClientBuilder cb = HttpClientBuilder.create();
		SSLContextBuilder sslcb = new SSLContextBuilder();
		sslcb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
		cb.setSSLContext(sslcb.build());
		this.httpClient = cb.build();
	}

	/**
	 * BunkerchanClientImpl constructor. Use the given HttpClient.
	 * 
	 * @param httpClient
	 */
	public BunkerchanClientImpl(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Page getPage(Channel channel) {
		try {
			URI uri = BUNKERCHAN_IMAGEBOARD.getURIRelativePath(channel.getChannelPath());
			Document doc = getDocument(uri);
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

}
