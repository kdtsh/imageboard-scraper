package net.kdtsh.is.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Abstract class for a post.
 * 
 * @author kdt
 *
 */
public abstract class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6716841220793596240L;

	@JsonProperty
	protected String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
