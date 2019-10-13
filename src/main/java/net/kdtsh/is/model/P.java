package net.kdtsh.is.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Abstract class for a `P' - the top level of a post containing some metadata.
 * 
 * @author kdt
 *
 */
@Component
public abstract class P implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676106632565129201L;

	protected String dataBoardUri;

	protected String id;

	public String getDataBoardUri() {
		return dataBoardUri;
	}

	public void setDataBoardUri(String dataBoardUri) {
		this.dataBoardUri = dataBoardUri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Convert the post, or posts, to JSON.
	 * 
	 * @return
	 */
	public abstract String toJson();

}
