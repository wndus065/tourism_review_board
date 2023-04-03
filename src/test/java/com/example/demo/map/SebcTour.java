package com.example.demo.map;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class SebcTour {
	SebcTourStreetKor sebcTourStreetKor;
}

@Setter
@ToString
class SebcTourStreetKor {
	int list_total_count;
	Result result;
	Row row;
}

@Setter
@ToString
class Result {
	String code;
	String Message;
}

@Setter
@ToString
class Row {
	String mainKey;
	String nmDp;
	String korAlias;
    String nameKor;
    String addKor;
    String lawSido;
    String lawSgg;
    String lawHemd;
    String hKorCity;
    String hKorGu;
    String hKorDong;
    double wgs84X;
    double wgs84Y;
}
