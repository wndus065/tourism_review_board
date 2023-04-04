package com.example.demo.map.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiDataLoader /*implements CommandLineRunner*/ {
    
//    @Autowired
//    private ApiRepository apiRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        String serviceKey = "4855574d5a736976353052516e6842";
//        String apiUrl = "http://openapi.seoul.go.kr:8088/" + serviceKey + "/json/SebcTourStreetKor/1/134/";
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<ApiDTO> response = restTemplate.getForEntity(apiUrl, ApiDTO.class);
//        ApiDTO apiDTO = response.getBody();
//
//        List<ApiEntity> apiEntities = apiDTO.getRow().stream()
//                .map(entity -> {
//                    ApiEntity apiEntity = new ApiEntity();
//                    apiEntity.setMainKey(entity.getMainKey());
//                    apiEntity.setNmDp(entity.getNmDp());
//                    apiEntity.setKorAlias(entity.getKorAlias());
//                    apiEntity.setNameKor(entity.getNameKor());
//                    apiEntity.setAddKor(entity.getAddKor());
//                    apiEntity.setLawSido(entity.getLawSido());
//                    apiEntity.setLawSgg(entity.getLawSgg());
//                    apiEntity.setLawHemd(entity.getLawHemd());
//                    apiEntity.setHKorCity(entity.getHKorCity());
//                    apiEntity.setHKorGu(entity.getHKorGu());
//                    apiEntity.setHKorDong(entity.getHKorDong());
//                    apiEntity.setWgs84X(entity.getWgs84X());
//                    apiEntity.setWgs84Y(entity.getWgs84Y());
//                    return apiEntity;
//                })
//                .collect(Collectors.toList());
//        
//        apiRepository.saveAll(apiEntities);
//    }
}