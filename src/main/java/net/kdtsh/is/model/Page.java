package net.kdtsh.is.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {

	@JsonProperty
	private List<P> pList;

	public List<P> getPList() {
		return pList;
	}

	public void setPList(List<P> pList) {
		this.pList = pList;
	}

}
