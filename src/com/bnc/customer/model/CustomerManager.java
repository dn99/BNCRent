package com.bnc.customer.model;

import java.util.List;

import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class CustomerManager {
	CustomerDAO customerDAO;
	
	public CustomerManager(){
		customerDAO = new CustomerDAO();
	}
	
	public List<CustomerTypeDTO> getBoardType() {
		return customerDAO.getBoardType();
	}
	
	public int getNextSeq() {
		return customerDAO.getNextSeq();
	}

	public int newArticle(CustomerDTO customerDTO) {
		return customerDAO.newArticle(customerDTO);
	}

	public List<CustomerDTO> getList(int btype, int pg, String item, String query, String myid) {
		int end = pg * PageSize.LIST_SIZE;
		int start = end - PageSize.LIST_SIZE;
		return customerDAO.getList(btype, end, start, item, query, myid);
	}

	public CustomerDTO getArticle(int seq, String func, int btype) {
		return customerDAO.getArticle(seq, func, btype);
	}

	public void updateHit(int seq) {
		customerDAO.updateHit(seq);
	}

	public int getNewArticleCount(int btype) {
		return customerDAO.getNewArticleCount(btype);
	}

	public PageNavi getPageNavi(int btype, int pg, String item, String query, String myid) {
		PageNavi navigator = new PageNavi();
		int totalArticle = customerDAO.getTotlaArticle(btype, item, query, myid);
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
		return customerDAO.deleteArticle(seq);
	}

	public CustomerDTO getModiArticle(int seq) {
		return customerDAO.getModiArticle(seq);
	}

	public int updateArticle(CustomerDTO customerDTO) {
		return customerDAO.updateArticle(customerDTO);
	}

	public int replyArticle(CustomerDTO customerDTO) {
		return customerDAO.replyArticle(customerDTO);
	}

}
