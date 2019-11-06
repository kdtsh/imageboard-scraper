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
