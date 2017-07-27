package com.github.kongchen.swagger.docgen;

import io.swagger.models.Tag;

public class CustomTag extends Tag {

	private int priority;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
