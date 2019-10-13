package net.kdtsh.is.model;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.stereotype.Component;

/**
 * Abstract class for the properties of a post.
 * 
 * @author kdt
 *
 */
@Component
public abstract class PostProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5898960434804703120L;

	protected String name;

	protected String email;

	protected Instant created;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

}
