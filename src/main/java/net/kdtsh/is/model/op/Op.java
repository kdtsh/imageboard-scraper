package net.kdtsh.is.model.op;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.P;
import net.kdtsh.is.model.cp.Cp;

/**
 * Original post.
 * 
 * @author kdt
 *
 */
public class Op extends P {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365622628456384974L;

	@JsonProperty
	private OpPost opPost;

	@JsonProperty
	private List<BigInteger> cpIdList;

	public OpPost getOpPost() {
		return opPost;
	}

	public void setOpPost(OpPost opPost) {
		this.opPost = opPost;
	}

	public List<BigInteger> getCpIdList() {
		return cpIdList;
	}

	public void setCpIdList(List<BigInteger> cpList) {
		this.cpIdList = cpList;
	}

}
