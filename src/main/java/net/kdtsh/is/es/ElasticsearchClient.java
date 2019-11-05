package net.kdtsh.is.es;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import net.kdtsh.is.imageboard.Imageboard;
import net.kdtsh.is.model.P;

/**
 * Interface defining the contract for interaction with Elasticsearch.
 * 
 * @author kdt
 *
 */
public interface ElasticsearchClient {

	/**
	 * POST a given {@link net.kdtsh.is.model.P P} to Elasticsearch.
	 * 
	 * @param p          a P.
	 * @param imageboard the Imageboard from which this P came from.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void postP(P p, Imageboard imageboard) throws ClientProtocolException, IOException;

	/**
	 * POST a List of {@link net.kdtsh.is.model.P P} to Elasticsearch.
	 * 
	 * @param pList      a List of Ps.
	 * @param imageboard the Imageboard from which these Ps came from.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void postPs(List<P> pList, Imageboard imageboard) throws ClientProtocolException, IOException;

}
