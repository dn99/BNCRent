package com.bnc.community.model;

import java.util.List;

import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class CommunityManager {
	CommunityDAO communityDAO;
	
	public CommunityManager(){
		communityDAO = new CommunityDAO();
	}
	
	public List<CommunityTypeDTO> getBoardType() {
		return communityDAO.getBoardType();
	}
	
	public int getNextSeq() {
		return communityDAO.getNextSeq();
	}

	public int newArticle(CommunityDTO communityDTO) {
		return communityDAO.newArticle(communityDTO);
	}

	public List<CommunityDTO> getList(int btype, int pg, String item, String query, String myid) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		return communityDAO.getList(btype, end, start, item, query, myid);
	}

	public CommunityDTO getArticle(int seq, String func, int btype) {
		return communityDAO.getArticle(seq, func, btype);
	}

	public void updateHit(int seq) {
		communityDAO.updateHit(seq);
	}

	public int getNewArticleCount(int btype) {
		return communityDAO.getNewArticleCount(btype);
	}

	public PageNavi getPageNavi(int btype, int pg, String item, String query, String myid) {
		PageNavi navigator = new PageNavi();
		int totalArticle = communityDAO.getTotlaArticle(btype, item, query, myid);
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

	public int deleteArticle(int seq) {
		return communityDAO.deleteArticle(seq);
	}

	public CommunityDTO getModiArticle(int seq) {
		return communityDAO.getModiArticle(seq);
	}

	public int updateArticle(CommunityDTO communityDTO) {
		return communityDAO.updateArticle(communityDTO);
	}

	public int replyArticle(CommunityDTO communityDTO) {
		return communityDAO.replyArticle(communityDTO);
	}

}
