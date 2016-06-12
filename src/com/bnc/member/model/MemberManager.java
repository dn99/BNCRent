package com.bnc.member.model;

import java.util.List;

import com.bnc.reserve.model.RentDTO;
import com.bnc.travel.model.TRentDTO;
import com.bnc.travel.model.TravelDTO;
import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class MemberManager {
	
	MemberDAO memberDAO;
	
	public MemberManager(){
		memberDAO = new MemberDAO();
	}
	
	public List<RentDTO> bookinglist(String id, int pg, String key, String word) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		
		return memberDAO.bookinglist(id, end, start, key, word);
		
	}

	public PageNavi getPageNavi(String id, int pg, String key, String word) {
		PageNavi navigator = new PageNavi();
		int totalArticle = memberDAO.getTotalArticle(key, word, id);
		navigator.setTotalArticle(totalArticle);
		int totalPage = (totalArticle + (PageSize.LIST_SIZE -1)) / PageSize.LIST_SIZE;
		navigator.setTotalPage(totalPage);
		navigator.setPageNo(pg);
		boolean nowFirst = pg <= PageSize.PAGE_SIZE ? true : false;
		navigator.setNowFirst(nowFirst);
		navigator.setNowEnd(isEnd(pg, totalPage));
		return navigator;
	}
	
	public PageNavi getPageNavi2(String id, int pg, String key, String word) {
		PageNavi navigator = new PageNavi();
		int totalArticle = memberDAO.getTotalArticle2(key, word, id);
		navigator.setTotalArticle(totalArticle);
		int totalPage = (totalArticle + (PageSize.LIST_SIZE -1)) / PageSize.LIST_SIZE;
		navigator.setTotalPage(totalPage);
		navigator.setPageNo(pg);
		boolean nowFirst = pg <= PageSize.PAGE_SIZE ? true : false;
		navigator.setNowFirst(nowFirst);
		navigator.setNowEnd(isEnd(pg, totalPage));
		return navigator;
	}
	
	public PageNavi getPageNavi3(String id, int pg, String key, String word) {
		PageNavi navigator = new PageNavi();
		int totalArticle = memberDAO.getTotalArticle3(key, word, id);
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

	public List<MemberDTO> list_admin(String id, int pg, String key, String word) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		
		return memberDAO.list_admin(id, end, start, key, word);
	}

	public List<TRentDTO> list_trent(String id, int pg, String key, String word) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		
		return memberDAO.list_trent(id, end, start, key, word);
	}
	
}
