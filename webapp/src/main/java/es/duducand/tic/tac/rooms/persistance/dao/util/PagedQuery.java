package es.duducand.tic.tac.rooms.persistance.dao.util;

import java.util.HashMap;
import java.util.Map;

public class PagedQuery {
	private int start = 0;
	private int end = -1;
	private String sort = null;
	private Map<String, String> constraints = new HashMap<String, String>();
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public String getSort() {
	        return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Map<String, String> getConstraints() {
		return constraints;
	}
	
	public void setConstraints(Map<String, String> constraints) {
		this.constraints = constraints;
	}
	
	public void addConstraint(String field, String value) {
		this.getConstraints().put(field, value);
	}
}
