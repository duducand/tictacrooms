package es.duducand.tic.tac.rooms.persistance.dao.util;

import java.util.Collections;
import java.util.List;

public class PagedResult<T> {
	private int total;
	private int start;
	private List<T> data;
	
	public PagedResult() {
		this(0, 0, Collections.<T>emptyList());
	}
	
	public PagedResult(int total, int start, List<T> data) {
		this.total = total;
		this.start = start;
		this.data = data;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> result) {
		this.data = result;
	}
}
