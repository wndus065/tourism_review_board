package com.example.demo.map.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class ApiEntity {

	@Id
	@JsonProperty("MAIN_KEY")
	private String mainKey;
	@JsonProperty("NM_DP")
	private String nmDp;
	@JsonProperty("KOR_ALIAS")
	private String korAlias;
	@JsonProperty("NAME_KOR")
	private String nameKor;
	@JsonProperty("ADD_KOR")
	private String addKor;
	@JsonProperty("LAW_SIDO")
	private String lawSido;
	@JsonProperty("LAW_SGG")
	private String lawSgg;
	@JsonProperty("LAW_HEMD")
	private String lawHemd;
	@JsonProperty("H_KOR_CITY")
	private String hKorCity;
	@JsonProperty("H_KOR_GU")
	private String hKorGu;
	@JsonProperty("H_KOR_DONG")
	private String hKorDong;
	@JsonProperty("WGS84_X")
	private double wgs84X;
	@JsonProperty("WGS84_Y")
	private double wgs84Y;

}
