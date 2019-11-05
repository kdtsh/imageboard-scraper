package net.kdtsh.is.model.cp;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.P;

/**
 * Child Post.
 * 
 * @author kdt
 *
 */
public class Cp extends P {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603521237035746138L;

	@JsonProperty
	private CpPost cpPost;

	private BigInteger opId;

	public CpPost getCpPost() {
		return cpPost;
	}

	public void setCpPost(CpPost cpPost) {
		this.cpPost = cpPost;
	}

	public BigInteger getOpId() {
		return opId;
	}

	public void setOpId(BigInteger opId) {
		this.opId = opId;
	}

}
