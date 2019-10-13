package net.kdtsh.is.model.cp;

import org.springframework.stereotype.Component;

import net.kdtsh.is.model.P;

/**
 * Child Post.
 * 
 * @author kdt
 *
 */
@Component
public class Cp extends P {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603521237035746138L;

	private CpPost cpPost;

	public CpPost getCpPost() {
		return cpPost;
	}

	public void setCpPost(CpPost cpPost) {
		this.cpPost = cpPost;
	}

	/**
	 * TODO implement this method.
	 */
	public String toJson() {
		return null;
	}
	
	@Override
	public String toString() {
		return String.join(",", id.toString(), cpPost.toString());		
	}

}
