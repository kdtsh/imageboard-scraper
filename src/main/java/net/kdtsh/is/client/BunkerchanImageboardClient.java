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

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.LoggerFactory;

import net.kdtsh.is.imageboard.Imageboard;
import net.kdtsh.is.imageboard.ImageboardUtils;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanImageboardUtils;

/**
 * ImageboardClient for Bunkerchan.
 * 
 * @author kdt
 *
 */
public class BunkerchanImageboardClient extends ImageboardClient {

	{
		logger = LoggerFactory.getLogger(BunkerchanImageboardClient.class);
	}

	/**
	 * 1-arg constructor. Generate a standard HttpClient, i.e. one which `ignores'
	 * SSL. Use the obvious ImageboardUtils.
	 * 
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	public BunkerchanImageboardClient(Imageboard imageboard) {
		this.imageboard = imageboard;
		this.imageboardUtils = new BunkerchanImageboardUtils(imageboard);
		try {
			HttpClientBuilder cb = HttpClientBuilder.create();
			SSLContextBuilder sslcb = new SSLContextBuilder();
			sslcb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
			cb.setSSLContext(sslcb.build());
			this.httpClient = cb.build();
		} catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * BunkerchanClientImpl constructor. Use the given HttpClient.
	 * 
	 * @param httpClient
	 */
	public BunkerchanImageboardClient(Imageboard imageboard, ImageboardUtils imageboardUtils, HttpClient httpClient) {
		this.imageboard = imageboard;
		this.imageboardUtils = imageboardUtils;
		this.httpClient = httpClient;
	}

}
