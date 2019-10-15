package net.kdtsh.is.model.cp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.Post;

/**
 * Child post uploads, properties, and contents.
 * 
 * @author kdt
 *
 */
public class CpPost extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1037915427277219998L;

	//
	//
	// TODO this needs to be fixed
	@JsonIgnore
	protected CpPostUploads cpPostUploads;

	@JsonProperty
	protected CpPostProperties cpPostProperties;

	public CpPostUploads getCpPostUploads() {
		return cpPostUploads;
	}

	public void setCpPostUploads(CpPostUploads cpPostUploads) {
		this.cpPostUploads = cpPostUploads;
	}

	public CpPostProperties getCpPostProperties() {
		return cpPostProperties;
	}

	public void setCpPostProperties(CpPostProperties cpPostProperties) {
		this.cpPostProperties = cpPostProperties;
	}

	public String toString() {
		return String.join(",", cpPostUploads.toString(), cpPostProperties.toString(), message.toString());
	}

}
