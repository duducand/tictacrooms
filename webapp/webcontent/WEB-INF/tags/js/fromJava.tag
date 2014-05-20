<%@tag import="java.beans.PropertyDescriptor"
%><%@tag import="java.beans.BeanInfo"
%><%@tag import="java.beans.Introspector"
%><%@tag import="java.io.StringWriter"
%><%@tag import="java.util.Collection"
%><%@tag import="java.util.Map"
%><%@tag import="java.util.HashMap"
%><%@tag import="java.util.ArrayList"
%><%@tag import="org.codehaus.jackson.map.ObjectMapper"
%><%@attribute name="object" rtexprvalue="true" type="java.lang.Object" required="true"
%><%@attribute name="properties" rtexprvalue="true" type="java.lang.String" required="false"
%><%!
private Map<String, Object> extractProperties(Object object, String[] properties) throws Exception {
	Map<String, Object> map = new HashMap<String, Object>();
	BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
	PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	for (int i = 0; i < properties.length; i++) {
		String propertyName = properties[i].trim();
		for (int j = 0; j < propertyDescriptors.length; j++) {
			if (propertyDescriptors[j].getName().equals(propertyName)) {
				map.put(propertyName, propertyDescriptors[j].getReadMethod().invoke(object));
			}
		}
	}
	return map;
}%><%
	if (properties != null) {
		String[] propertyArray = properties.split(",");
		for (int i = 0; i < propertyArray.length; i++) {
			propertyArray[i] = propertyArray[i].trim();
		}
		if (object instanceof Map<?, ?>) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			for (int i = 0; i < propertyArray.length; i++) {
				map.put(propertyArray[i], ((Map<?, ?>) object).get(propertyArray[i]));
			}
			object = map;
		} else {
			if (object instanceof Collection<?>) {
				Collection<Object> collection = new ArrayList<Object>();
				for (Object item: (Collection<?>) object) {
					collection.add(extractProperties(item, propertyArray));
				}
				object = collection;
			} else {
				object = extractProperties(object, propertyArray);
			}
		}
	}
	ObjectMapper mapper = new ObjectMapper();
	StringWriter writer = new StringWriter();
	mapper.writeValue(writer, object);
	out.write(writer.toString());
%>