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
 * Hello world!
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
