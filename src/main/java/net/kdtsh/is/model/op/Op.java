package net.kdtsh.is.model.op;

import java.util.List;

import org.springframework.stereotype.Component;

import net.kdtsh.is.model.P;
import net.kdtsh.is.model.cp.Cp;

/**
 * Original post.
 * 
 * @author kdt
 *
 */
@Component
public class Op extends P {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365622628456384974L;

	private OpPost opPost;

	private List<Cp> cpList;

	public OpPost getOpPost() {
		return opPost;
	}

	public void setOpPost(OpPost opPost) {
		this.opPost = opPost;
	}

	public List<Cp> getCpList() {
		return cpList;
	}

	public void setCpList(List<Cp> cpList) {
		this.cpList = cpList;
	}

	/**
	 * TODO implement this method.
	 */
	public String toJson() {
		return null;
	}

	@Override
	public String toString() {
		StringBuilder cpListSb = new StringBuilder();
		for (Cp cp : cpList) {
			cpListSb.append(cp.toString() + "\n");
		}
		return String.join(",", opPost.toString(), cpListSb.toString());
	}
	
}
