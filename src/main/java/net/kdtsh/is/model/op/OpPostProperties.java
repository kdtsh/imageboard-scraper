package net.kdtsh.is.model.op;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.PostProperties;

/**
 * Original post properties.
 * 
 * @author kdt
 *
 */
public class OpPostProperties extends PostProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014078241191825447L;

	@JsonProperty
	private String postSubject;

	@JsonProperty
	private String threadLink;

	public String getPostSubject() {
		return postSubject;
	}

	public void setPostSubject(String postSubject) {
		this.postSubject = postSubject;
	}

	public String getThreadLink() {
		return threadLink;
	}

	public void setThreadLink(String threadLink) {
		this.threadLink = threadLink;
	}

	public String toString() {
		return String.join(",", postSubject, threadLink, name, email, created.toString());
	}

}
