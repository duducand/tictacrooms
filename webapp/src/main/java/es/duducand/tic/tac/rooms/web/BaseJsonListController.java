package es.duducand.tic.tac.rooms.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;


public abstract class BaseJsonListController<ItemType> {
    private final Log logger = LogFactory.getLog(getClass());
	private PermissionEvaluator permissionEvaluator;

    @Autowired
	public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}
    
    public PermissionEvaluator getPermissionEvaluator() {
        return permissionEvaluator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public void onGet(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
    	PagedQuery query = getQuery(request);
    	PagedResult<ItemType> result = getResult(query);
    	List<Map<String, Object>> fullResult = getFullResult(authentication, result);
    	sendResult(response, result, fullResult);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getItem(Authentication authentication, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
    	ItemType item = getResultItem(id);
    	Map<String, Object> fullItem = getFullItem(authentication, item);
    	sendItem(response, fullItem);
    }
    
    protected abstract PagedResult<ItemType> getResult(PagedQuery query);
    
    protected abstract ItemType getResultItem(String id);
    
    protected abstract Map<String, Object> getMap(ItemType object);
	
	protected PagedQuery getQuery(HttpServletRequest request) {
    	PagedQuery query = new PagedQuery();
    	
    	for (Object paramObject : request.getParameterMap().entrySet()) {
    		Map.Entry<?, ?> paramEntry = (Map.Entry<?, ?>) paramObject;
    		String paramName = recode((String) paramEntry.getKey());
    		String[] paramValues = recode((String[]) paramEntry.getValue());
    		if (paramName.startsWith("sort(") && paramName.endsWith(")")) {
    			query.setSort(paramName.substring("sort(".length(), paramName.length() - "(".length()));
    		} else {
    			query.addConstraint(paramName, paramValues[0].replace('*', '%').replace('.', '_'));
    		}
    	}
    	
    	int start = 0;
    	int end = 25;
    	String range = request.getHeader("Range");
    	if (range != null && range.startsWith("items=")) {
    		range = range.substring("items=".length());
    		String[] limits = range.split("-");
    		if (limits.length == 2) {
    			try {
    				start = Integer.parseInt(limits[0]);
    			} catch (NumberFormatException e) {}
    			try {
    				end = Integer.parseInt(limits[1]);
    			} catch (NumberFormatException e) {
    				end = start + 25;
    			}
    		}
    	}
    	query.setStart(start);
    	query.setEnd(end);

    	return query;
	}
	
	protected List<Map<String, Object>> getFullResult(Authentication authentication, PagedResult<ItemType> result) {
    	List<Map<String, Object>> fullResult = new ArrayList<Map<String, Object>>();
    	for (ItemType item: result.getData()) {
    		fullResult.add(getFullItem(authentication, item));
    	}
    	return fullResult;
	}
	
	protected Map<String, Object> getFullItem(Authentication authentication, ItemType item) {
		Map<String, Object> itemMap = getMap(item);
		return itemMap;
	}

	protected void sendResult(HttpServletResponse response, PagedResult<ItemType> result, List<Map<String, Object>> fullResult) {
		response.setHeader("Content-Range", String.format("items %d-%d/%d",
				result.getStart(), result.getStart() + result.getData().size() - 1, result.getTotal()));
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		try {
			jsonConverter.write(fullResult, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
		} catch (Exception e) {
			logger.error("Error convirtiendo en JSON", e);
		}
	}
	
	protected void sendItem(HttpServletResponse response, Map<String, Object> fullItem) {
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		try {
			jsonConverter.write(fullItem, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
		} catch (Exception e) {
			logger.error("Error convirtiendo en JSON", e);
		}
	}
	
    private String recode(String string) {
    	try {
    		return new String(string.getBytes(), "UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		return string;
    	}
    }
    
    private String[] recode(String[] strings) {
    	String[] recoded = new String[strings.length];
    	for (int i = 0; i < strings.length; i++) {
    		recoded[i] = recode(strings[i]);
    	}
    	return recoded;
    }
}
