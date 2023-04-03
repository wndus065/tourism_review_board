package com.example.demo.map.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiDTO {
	private int listTotalCount;
	private Result result;
	private List<ApiEntity> row;

	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Result {
		@JsonProperty("code")
		private String CODE;
		@JsonProperty("message")
		private String MESSAGE;
	}
}
