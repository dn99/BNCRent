package com.bnc.travel.model;

import java.util.List;
import java.util.Map;

import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class TravelManager {
	TravelDAO travelDAO;
	
	public TravelManager(){
		travelDAO = new TravelDAO();
	}

	public List<TravelDTO> getList(int pg, String key, String word) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		
		System.out.println("trvelController end ===>>> " + end);
		System.out.println("trvelController start ===>>> " + start);
		
		return travelDAO.getList(end, start, key, word);
		
	}

	public PageNavi getPageNavi(int pg, String key, String word) {
		PageNavi navigator = new PageNavi();
		int totalArticle = travelDAO.getTotalArticle(key, word);
		navigator.setTotalArticle(totalArticle);
		int totalPage = (totalArticle + (PageSize.LIST_SIZE -1)) / PageSize.LIST_SIZE;
		navigator.setTotalPage(totalPage);
		navigator.setPageNo(pg);
		boolean nowFirst = pg <= PageSize.PAGE_SIZE ? true : false;
		navigator.setNowFirst(nowFirst);
		navigator.setNowEnd(isEnd(pg, totalPage));
		return navigator;
	}
	
	private boolean isEnd(int pg, int totalPage) {
		if((totalPage - 1)/ PageSize.PAGE_SIZE * PageSize.PAGE_SIZE < pg)
			return true;
		return false;
	}

	public List<CarInformationDTO> getRentCarList(int pg, int roffice, int grade) {
		int end = pg * PageSize.LIST_SIZE_RESERVE;
		int start = end - PageSize.LIST_SIZE_RESERVE;
		
		return travelDAO.getRentCarList(end, start, roffice, grade);
	}
}
