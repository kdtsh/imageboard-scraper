package net.kdtsh.is.model.cp;

import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kdtsh.is.model.PostProperties;

/**
 * Child post properties.
 * 
 * @author kdt
 *
 */
public class CpPostProperties extends PostProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273160562053485669L;

	public String toString() {
		return String.join(",", name, email, created.toString());
	}

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();

		CpPostProperties cpp = new CpPostProperties();
		cpp.setCreated(Instant.now());
		cpp.setEmail("kenan@kdtsh.net");
		cpp.setName("Anonymous");

		String jsonString = om.writeValueAsString(cpp);
		System.out.println(jsonString);
	}

}
