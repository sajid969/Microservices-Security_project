package com.tcs.exceptionalhandling;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter

public class ResourceNotFound extends RuntimeException {
	
	public ResourceNotFound() {
		super("Resource not found");
	}
	
	public ResourceNotFound(String message) {
		super(message);
	}
}
