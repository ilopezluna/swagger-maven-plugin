package com.github.kongchen.swagger.docgen;

import io.swagger.models.Operation;

public class CustomOperation extends Operation {

	private int priority;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
