package kr.or.ddit.controller.ch14.item02.service;

import java.util.List;

import kr.or.ddit.vo.Item2;

public interface ItemService2 {
	public List<Item2> list();
	public void register(Item2 item);
}