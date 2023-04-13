package com.example.demo.placeBoard.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	
	@Value("${imgPath}")
	String path;
//	String path = "C:\\Users\\user\\Desktop\\BackEnd_Project\\uploadfile";
	
	public String fileUpload(MultipartFile multipartFile) {
		Path copyOfLocation = null;
		
		if(multipartFile.isEmpty()) { //파일스트림이 없으면 메소드를 종료한다
			return null;
		}
		try {
			copyOfLocation = Paths
					.get(path + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename())); //파일 전체 경로
			
			Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING); //파일을 폴더에 저장
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//파일이름을 반환한다
		return multipartFile.getOriginalFilename();
	}
	
}
