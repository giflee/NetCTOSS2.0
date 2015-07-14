package com.neu.jianfu.page;

import java.io.Serializable;

public class CostPage implements Serializable {
	//��ҳ����֪������������
	//��ҳsql��������������������
	private int currentPage = 1;
	private int pageSize = 5;//�̶���ֵ
	private int begin;
	private int end;
	private int row;
	private int totalPage;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getTotalPage() {
		if(row%pageSize == 0){
			totalPage = row/pageSize;
		}else{
			totalPage = row/pageSize + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBegin() {
		//������ʼ��
		begin = (currentPage-1)*pageSize+1;
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		end = currentPage*pageSize;
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}	
