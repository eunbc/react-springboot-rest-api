package com.ebcho.marketkurly.advice;

public record ErrorResponse(String message) {

	public static ErrorResponse of(String message) {
		return new ErrorResponse(message);
	}
}
