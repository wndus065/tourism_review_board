package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.user.dto.CustomUser;
import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.service.MemberService;

@Service
public class UserDetailsServicelmpl implements UserDetailsService {
	
	@Autowired
	private MemberService service;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberDTO dto = service.read(id);
 		if(dto == null) {
			throw new UsernameNotFoundException("");
		}else {
		return new CustomUser(dto);	
		}
		
	}

}
