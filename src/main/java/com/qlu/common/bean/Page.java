package com.qlu.common.bean;

import com.qlu.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
	private int pageNum; // 当前页
	private int pageSize;
	private List<T> rows;
	private long total; // 总记录数
	private int pageCount; // 总页数
	private int pre; // 上一页
	private int next; // 下一页
	private boolean first; // 首页
	private boolean last; // 尾页


	//转化成bootStrapTable的page
	public Page(Integer pageSize, com.baomidou.mybatisplus.extension.plugins.pagination.Page page_mybatisPlus) {
		List rows = page_mybatisPlus.getRecords();
		int pageNum = (int)page_mybatisPlus.getCurrent();
		int pages = (int)page_mybatisPlus.getPages();
		int total = (int)page_mybatisPlus.getTotal();
		this.setRows(rows);
		this.setPageNum(pageNum);
		this.setPageSize(pageSize);
		this.setNext(pageNum+1);
		this.setPre(pageNum-1);
		this.setFirst(pageNum==1);
		this.setLast(pageNum==(total/pageSize+1));
		this.setTotal(total);
		this.setPageCount(pages);
	}

}
