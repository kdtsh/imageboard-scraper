package net.kdtsh.is.model.cp;

import org.springframework.stereotype.Component;

import net.kdtsh.is.model.PostProperties;

/**
 * Child post properties.
 * 
 * @author kdt
 *
 */
@Component
public class CpPostProperties extends PostProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273160562053485669L;
	
	public String toString() {
		return String.join(",", name, email, created.toString());
	}

}
