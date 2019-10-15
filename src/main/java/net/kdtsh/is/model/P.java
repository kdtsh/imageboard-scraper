package net.kdtsh.is.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Abstract class for a `P' - the top level of a post containing some metadata.
 * 
 * @author kdt
 *
 */
public abstract class P implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676106632565129201L;

	@JsonProperty("dataBoardUri")
	protected String dataBoardUri;

	@JsonProperty("id")
	protected BigInteger id;

	public String getDataBoardUri() {
		return dataBoardUri;
	}

	public void setDataBoardUri(String dataBoardUri) {
		this.dataBoardUri = dataBoardUri;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * Convert the post, or posts, to JSON.
	 * 
	 * @return
	 */
	public abstract String toJson();

}
