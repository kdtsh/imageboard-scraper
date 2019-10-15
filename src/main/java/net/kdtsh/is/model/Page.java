package net.kdtsh.is.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kdtsh.is.model.op.Op;

public class Page {

	@JsonProperty
	private List<Op> opList;

	public List<Op> getOpList() {
		return opList;
	}

	public void setOpList(List<Op> opList) {
		this.opList = opList;
	}

}
