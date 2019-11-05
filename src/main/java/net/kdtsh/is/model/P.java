package net.kdtsh.is.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract class for a `P' - the top level of a post containing some metadata.
 * 
 * @author kdt
 *
 */
public abstract class P implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676106632565129201L;

	@JsonProperty("boardName")
	protected String boardName;

	@JsonProperty("id")
	protected BigInteger id;

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * Convert this object to JSON.
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}

}
