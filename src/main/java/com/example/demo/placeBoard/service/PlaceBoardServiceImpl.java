package com.example.demo.placeBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.comment.service.CommentService;
import com.example.demo.interest.service.InterestService;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.placeBoard.util.FileUtil;
import com.example.demo.user.entity.Member;

@Service
public class PlaceBoardServiceImpl implements PlaceBoardService {

	@Autowired
	private PlaceBoardRepository repository;

	@Autowired
	private CommentService commentService;

	@Autowired
	private InterestService interService;

	@Autowired
	private FileUtil fileUtil;

	@Override
	public int register(PlaceBoardDTO dto) {
		PlaceBoard entity = dtoToEntity(dto);

		String imgPath = fileUtil.fileUpload(dto.getUploadFile());
		entity.setImgPath(imgPath);

		System.out.println(entity);
		repository.save(entity);

		return entity.getNo();
	}

	@Override
	public Page<PlaceBoardDTO> getList(int page) {
		int pageNum = (page == 0) ? 0 : page - 1; // page는 index 처럼 0부터 시작
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
		Page<PlaceBoard> entityPage = repository.findAll(pageable);
		Page<PlaceBoardDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public PlaceBoardDTO read(int no) {
		Optional<PlaceBoard> result = repository.findById(no);
		if (result.isPresent()) {
			PlaceBoard placeBoard = result.get();
			return entityToDto(placeBoard);
		} else {
			return null;
		}
	}

	@Override
	public void modify(PlaceBoardDTO dto) {
		Optional<PlaceBoard> result = repository.findById(dto.getNo());
		if (result.isPresent()) {
			PlaceBoard entity = result.get();
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			entity.getModDate();
			repository.save(entity);
		}
	}

	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}

	@Override
	public void delFkPost(String writerNo) {
		List<PlaceBoard> list = repository.findAllByWriter(Member.builder().id(writerNo).build());
		for (PlaceBoard post : list) {
			commentService.delFkCom(post.getNo());
			interService.delFkInter(post.getNo());
			repository.delete(post);
		}
	}

	@Override
	public void delFkPostP(String place) {
		List<PlaceBoard> list = repository.findAllByPlace(MapEntity.builder().place(place).build());
		for (PlaceBoard post : list) {
			commentService.delFkCom(post.getNo());
			interService.delFkInter(post.getNo());
			repository.delete(post);
		}
	}
	
	@Override
   public List<PlaceBoard> searchByContent(String content) {
      return repository.findByContentContaining(content);
   }

   @Override
   public List<PlaceBoard> searchByPlace(MapEntity place) {
      return repository.findByPlace(place);
   }

   @Override
   public List<PlaceBoard> searchByTitle(String title) {
      return repository.findByTitleContaining(title);
   }

   @Override
   public List<PlaceBoard> searchByWriter(Member writer) {
      return repository.findByWriter(writer);
   }


}
