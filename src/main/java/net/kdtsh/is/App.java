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
package net.kdtsh.is;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;

import net.kdtsh.is.client.BunkerchanClientImpl;
import net.kdtsh.is.client.ImageboardClient;
import net.kdtsh.is.es.ElasticsearchClient;
import net.kdtsh.is.es.ElasticsearchClientImpl;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanChannel;
import net.kdtsh.is.imageboard.bunkerchan.BunkerchanImageboard;
import net.kdtsh.is.model.Page;

/**
 * Application main method.
 * 
 * @author kdt
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Getting /leftypol/ ...");
		doWork();
		System.out.println("Done.");
	}

	public static void doWork() {

		try {
			ImageboardClient ic = new BunkerchanClientImpl();
			Page page = ic.getPage(BunkerchanChannel.checkChannel("leftypol"));
			ElasticsearchClient ec = new ElasticsearchClientImpl();
			ec.postPs(page.getPList(), new BunkerchanImageboard());
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
