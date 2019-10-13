package net.kdtsh.is.model.op;

import org.springframework.stereotype.Component;

import net.kdtsh.is.model.Post;

/**
 * Original post uploads, properties, and contents.
 * 
 * @author kdt
 *
 */
@Component
public class OpPost extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6171562940377448035L;

	private OpPostUploads opPostUploads;

	private OpPostProperties opProperties;

	public OpPostUploads getOpPostUploads() {
		return opPostUploads;
	}

	public void setOpPostUploads(OpPostUploads opPostUploads) {
		this.opPostUploads = opPostUploads;
	}

	public OpPostProperties getOpProperties() {
		return opProperties;
	}

	public void setOpProperties(OpPostProperties opProperties) {
		this.opProperties = opProperties;
	}
	
	public String toString() {
		return String.join(",", opPostUploads.toString(), opProperties.toString(), message);
	}

}
