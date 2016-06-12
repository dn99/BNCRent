package com.bnc.rentcar.model;

import java.util.List;

import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class CarManager {
	CarInformationDAO carinformationDAO;
	
	public CarManager(){
		carinformationDAO = new CarInformationDAO();
	}

	public List<CarInformationDTO> getList(int pg) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		
		return carinformationDAO.getlist(end, start);
	}
	
	public List<CarInformationDTO> getPlist(int pg, String tab, String key, String word) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		
		return carinformationDAO.getplist(end, start, tab, key, word);
	}
	
	public int deleteArticle(int seq) {
		return carinformationDAO.deleteArticle(seq);
	}

	public PageNavi getPageNavi(int pg, String tab, String key, String word) {
		PageNavi navigator = new PageNavi();
		int totalArticle = carinformationDAO.getTotalArticle(tab, key, word);
		navigator.setTotalArticle(totalArticle);
		
		int totalPage = (totalArticle + (PageSize.LIST_SIZE -1)) / PageSize.LIST_SIZE;
		navigator.setTotalPage(totalPage);
		navigator.setPageNo(pg);
		
		boolean nowFirst = pg <= PageSize.PAGE_SIZE ? true : false;
		navigator.setNowFirst(nowFirst);
		navigator.setNowEnd(isEnd(pg, totalPage));
		return navigator;
	}
	
	public PageNavi getPageNavi(int pg) {
		PageNavi navigator = new PageNavi();
		int totalArticle = carinformationDAO.getTotalArticleu();
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
}


