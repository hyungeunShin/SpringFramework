package kr.or.ddit.controller.ch14.item02.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.ItemMapper2;
import kr.or.ddit.vo.Item2;

@Service
public class ItemServiceImpl2 implements ItemService2 {
	
	@Inject
	private ItemMapper2 mapper;
	
	@Override
	public List<Item2> list() {
		return mapper.list();
	}

	@Override
	public void register(Item2 item) {
		mapper.register(item);
	}
}
