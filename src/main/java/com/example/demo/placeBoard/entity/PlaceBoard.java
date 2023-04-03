package com.example.demo.placeBoard.entity;

<<<<<<< HEAD
import com.example.demo.map.entity.MapEntity;
import com.example.demo.user.entity.Member;
=======
import java.util.ArrayList;
import java.util.List;

import com.example.demo.interest.entity.Interest;
>>>>>>> branch 'develop' of https://gitlab.com/greenart_project/project1.git

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.ManyToOne;
=======
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
>>>>>>> branch 'develop' of https://gitlab.com/greenart_project/project1.git
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceBoard extends BaseEntity {
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    
	@ManyToOne //글쓴이
    private Member writer;
    
	@ManyToOne //장소
    private MapEntity place; 
    
    @Column(length = 50, nullable = false) //글제목
    private String title;
    
    @Column(length = 500) //글내용
    private String content;
    
 
    
   
	
	

}
