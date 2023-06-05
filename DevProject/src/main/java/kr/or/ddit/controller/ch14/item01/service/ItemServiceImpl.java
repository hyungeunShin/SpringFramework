package kr.or.ddit.controller.ch14.item01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.ItemMapper;
import kr.or.ddit.vo.Item;

@Service
public class ItemServiceImpl implements IItemService {
	
	@Inject
	private ItemMapper itemMapper;
	
	@Override
	public void register(Item item) {
		itemMapper.create(item);
	}

	@Override
	public List<Item> list() {
		return itemMapper.list();
	}

	@Override
	public Item read(int itemId) {
		return itemMapper.read(itemId);
	}

	@Override
	public String getPicture(int itemId) {
		return itemMapper.getPicture(itemId);
	}

	@Override
	public void modify(Item item) {
		itemMapper.modify(item);
	}

	@Override
	public void remove(int itemId) {
		itemMapper.remove(itemId);
	}
}
