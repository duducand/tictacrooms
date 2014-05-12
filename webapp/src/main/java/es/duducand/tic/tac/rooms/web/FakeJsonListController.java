package es.duducand.tic.tac.rooms.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;

public abstract class FakeJsonListController extends BaseJsonListController<Map<String, Object>> {
    @Override
    protected PagedResult<Map<String, Object>> getResult(PagedQuery query) {
    	List<Map<String, Object>> filtered = filter(getElements(), query.getConstraints());
    	List<Map<String, Object>> sorted = sort(filtered, query.getSort());
    	List<Map<String, Object>> result = range(sorted, query.getStart(), query.getEnd());
    	return new PagedResult<Map<String,Object>>(filtered.size(), query.getStart(), result);
    }
    
    @Override
    protected Map<String, Object> getResultItem(String id) {
    	for (Map<String, Object> instrument: getElements()) {
    		if (instrument.get(getIdField()).equals(id)) {
    			return instrument;
    		}
    	}
    	return null;
    }
    
    @Override
    protected Map<String, Object> getMap(Map<String, Object> instrument) {
    	return new HashMap<String, Object>(instrument);
    }
    
    protected abstract List<Map<String, Object>> getElements();
    
    protected abstract String getIdField();
    
    protected String randomString(boolean upper, int length) {
    	int a = Character.codePointAt(upper ? "A" : "a", 0);
    	int z = Character.codePointAt(upper ? "Z" : "z", 0);
    	StringBuffer buffer = new StringBuffer();
    	for (int i = 0; i < length; i++) {
	    	int x = (int) (a + (z - a) * Math.random());
	    	buffer.append(Character.toChars(x));
    	}
    	return buffer.toString();
    }
    
	@SuppressWarnings("unchecked")
    private List<Map<String, Object>> sort(List<Map<String, Object>> elements, String sort) {
    	List<Map<String, Object>> sorted = new ArrayList<Map<String, Object>>(elements);
		if (sort != null) {
			String[] sortFields = sort.split(",");
			for (int i = sortFields.length - 1; i >= 0; i--) {
				char direction = sortFields[i].charAt(0);
				final String field = sortFields[i].substring(1);
				if (direction == '+' || direction == ' ') {
					Collections.sort(sorted, new Comparator<Map<String, Object>>() {
						@SuppressWarnings("rawtypes")
						public int compare(Map<String, Object> o1, Map<String, Object> o2) {
							if (o1.get(field) == null) {
								return 1;
							} else if (o2.get(field) == null) {
								return -1;
							} else if (o1.get(field) instanceof Comparable) {
								return ((Comparable) o1.get(field)).compareTo(o2.get(field));
							} else {
								return 0;
							}
						}
					});
				} else if (direction == '-') {
					Collections.sort(sorted, new Comparator<Map<String, Object>>() {
						@SuppressWarnings("rawtypes")
						public int compare(Map<String, Object> o1, Map<String, Object> o2) {
							if (o1.get(field) == null) {
								return 1;
							} else if (o2.get(field) == null) {
								return -1;
							} else if (o1.get(field) instanceof Comparable) {
								return ((Comparable) o2.get(field)).compareTo(o1.get(field));
							} else {
								return 0;
							}
						}
					});
				}
			}
		}
		return sorted;
    }
    
    private List<Map<String, Object>> filter(List<Map<String, Object>> elements, Map<String, String> filter) {
    	Map<String, Pattern> patterns = new HashMap<String, Pattern>();
    	if (filter != null) {
    		for (Map.Entry<String, String> entry: filter.entrySet()) {
    			String value = entry.getValue();
    			value = value.replaceAll("[%]", ".*");
    			value = value.replaceAll("[_]", ".");
    			Pattern pattern = Pattern.compile("^" + value + "$", Pattern.CASE_INSENSITIVE);
    			patterns.put(entry.getKey(), pattern);
    		}
    	}
    	
    	List<Map<String, Object>> filtered = new ArrayList<Map<String, Object>>();
    	for (Map<String, Object> element: elements) {
    		boolean matches = true;
    		for (Map.Entry<String, Pattern> entry: patterns.entrySet()) {
    			if (element.containsKey(entry.getKey())) {
    				if (!entry.getValue().matcher((String) element.get(entry.getKey())).matches()) {
    					matches = false;
    					break;
    				}
    			}
    		}
    		if (matches) {
				filtered.add(new HashMap<String, Object>(element));
    		}
    	}
    	
    	return filtered;
    }
    
    private List<Map<String, Object>> range(List<Map<String, Object>> elements, int start, int end) {
       int localEnd = end;
    	if (start >= elements.size()) {
    		return Collections.emptyList();
    	} else {
    		if (localEnd < elements.size()) {
    			localEnd++;
    		} else {
    			localEnd = elements.size();
    		}
    		return new ArrayList<Map<String, Object>>(elements.subList(start, localEnd));
    	}
    }
}
