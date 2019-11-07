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

import org.apache.http.client.ClientProtocolException;

import net.kdtsh.is.model.Page;

/**
 * Interface defining the contract for interaction with Elasticsearch.
 * 
 * @author kdt
 *
 */
public interface ElasticsearchClient {

	/**
	 * POST a Page to Elasticsearch.
	 * 
	 * @param page a Page.
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void postPage(Page page) throws ClientProtocolException, IOException;

}
