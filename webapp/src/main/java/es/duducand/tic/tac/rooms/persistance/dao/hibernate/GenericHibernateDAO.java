package es.duducand.tic.tac.rooms.persistance.dao.hibernate;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import es.duducand.tic.tac.rooms.persistance.dao.GenericDAO;
import es.duducand.tic.tac.rooms.persistance.dao.security.EstadoUsuario;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;

public class GenericHibernateDAO<T, PK extends Serializable> 
                    				   extends HibernateDaoSupport
                                       implements Serializable,GenericDAO<T, PK>  {
	/**
     * 
     */
    private static final long serialVersionUID = -5595720605440055914L;
    private Class<T> persistentClass;
    private EstadoUsuario estadoUsuario;
	private static final String PUNTO_ESCAPADO="\\.";
	private static final String NOT_LLAVE="NOT{";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 
	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
	public EstadoUsuario getEstadoUsuario() {
		return estadoUsuario;
	}
		

    public T getById(PK id, boolean lock) {
        T entity;
        if (lock){
            entity = (T) getHibernateTemplate().load(getPersistentClass(), id, LockMode.UPGRADE);
        }else{
            entity = (T) getHibernateTemplate().load(getPersistentClass(), id);
        }
        return entity;
    }

    public List<T> getAll() {
        return getHibernateTemplate().loadAll(getPersistentClass());
    }

    public List<T> getByExample(T exampleInstance) {
        return findByCriteria( Example.create(exampleInstance) );
    }

    public T save(T entity) {
    	//entity = getHibernateTemplate().merge(entity); 
    	getHibernateTemplate().saveOrUpdate(entity);
        return entity;
    }

    public T merge(T entity) {
       return getHibernateTemplate().merge(entity);
    }
    
    public void delete(T entity) {
    	getHibernateTemplate().delete(entity);
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
    	DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
        for (Criterion c : criterion) {
        	criteria.add(c);
        }
        return getHibernateTemplate().findByCriteria(criteria);
    }
    
    public Class<T> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@SuppressWarnings("unchecked")
    public List<T> findByNamedQueryAndNamedParam(Class<T> entityClass,
            String queryName, String[] paramNames, Object[] values)  {

        return (List<T>) getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
    }

    public List<T> findByNamedQueryAndNamedParam(Class<T> entityClass,
            String queryName, Map<String, ?> params) {

        String[] paramNames = new String[params.size()];
        Object[] values = new Object[params.size()];
        
        List<String> keys = new ArrayList<String>(params.keySet());
        for(int i=0; i<keys.size(); i++){
            String k = keys.get(i);
            paramNames[i] = k;
            values[i] = params.get(k);
        }
        
        return this.findByNamedQueryAndNamedParam(entityClass, queryName, paramNames, values);
    }

    
    @SuppressWarnings("unchecked")
    public List<T> findByPagedQuery(PagedQuery query) {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistentClass);
        Map<String, DetachedCriteria> mapaCriterios=this.createCriterias(criteria,query.getConstraints(),query.getSort());
        this.addQueryRestrictions(criteria, query.getConstraints(),query.getSort(),mapaCriterios);
        this.addOrder(criteria, query.getSort(),mapaCriterios);
        //criteria.add(Restrictions.allEq(query.getConstraints()));
        if (query.getEnd() < 0) {
            return getHibernateTemplate().findByCriteria(criteria);
        } else {
            return getHibernateTemplate().findByCriteria(criteria, query.getStart(), query.getEnd() - query.getStart() + 1);
        }
    }

    public int countByPagedQuery(PagedQuery query) {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistentClass);
        criteria.setProjection(Projections.rowCount());
        Map<String, DetachedCriteria> mapaCriterios=this.createCriterias(criteria,query.getConstraints(),query.getSort());
//        this.crearAlias(criteria,query.getConstraints(),query.getSort());
        this.addQueryRestrictions(criteria, query.getConstraints(),query.getSort(),mapaCriterios);
        //criteria.add(Restrictions.allEq(query.getConstraints()));
        return DataAccessUtils.intResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    protected Map<String, DetachedCriteria> createCriterias(DetachedCriteria criteria, Map<String, String> constraints,String sort) {
        HashMap<String, DetachedCriteria> mapaCriterios=new HashMap<String, DetachedCriteria>();
        for (Map.Entry<String, String> entry: constraints.entrySet()) {
            creaCriteriasUnitarias(entry,mapaCriterios,criteria);
        }
        if(sort!=null){
              String [] listaOrdenados = sort.trim().replace("+", "").replace("-", "").replace("&&", ",").split(",");
              for(String orden: listaOrdenados){
                  anyadirOrdenUnitario(orden,mapaCriterios,criteria);
              }
        }
        return mapaCriterios;
    }
    private String mergeStrings(String[] split,int inicio,int fin){
        StringBuilder resultado = new StringBuilder();
        for(int i = inicio;i<=fin;i++){
            resultado.append(split[i]);
            if(i<fin){
                resultado.append(".");
            }
        }
        return resultado.toString();
    }
    protected void addQueryRestrictions(DetachedCriteria criteria, Map<String, String> constraints) {
        this.addQueryRestrictions(criteria, constraints,null,null);
    }
    
    protected void addQueryRestrictions(DetachedCriteria criteria, Map<String, String> constraints,String sort,Map<String, DetachedCriteria> mapaCriterios) {
//        this.crearAlias(criteria,constraints,sort);
        for (Map.Entry<String, String> entry: constraints.entrySet()) {
            DetachedCriteria subCriteria = this.getCriteria(criteria,entry.getKey(),mapaCriterios);
            String[] split = entry.getKey().split(PUNTO_ESCAPADO);
            String clave = entry.getKey();
            if(split.length>1){
                clave = split[split.length-1];
            }
            if(clave.equals("id")){
                if(entry.getValue().startsWith(NOT_LLAVE) && entry.getValue().endsWith("}")){
                    String[] ids = entry.getValue().replace(NOT_LLAVE, "").replace("}", "").split(",");
                    Integer[] idsNumericos = new Integer[ids.length];
                    int i = 0;
                    for(String id : ids){
                        if(!id.equals("")){
                            idsNumericos[i++]=Integer.parseInt(id);
                        }
                    }
                    if(i>0){
                        subCriteria.add(Restrictions.not(Restrictions.in(clave,idsNumericos )));
                    }
                }else if(entry.getValue().startsWith("{") && entry.getValue().endsWith("}")){
                    String[] ids = entry.getValue().replace("{", "").replace("}", "").split(",");
                    Integer[] idsNumericos = new Integer[ids.length];
                    int i = 0;
                    for(String id : ids){
                        if(!id.equals("")){
                            idsNumericos[i++]=Integer.parseInt(id);
                        }
                    }
                    subCriteria.add(Restrictions.in(clave,idsNumericos ));
                } 
                else{
                    subCriteria.add(Restrictions.eq(clave, Integer.parseInt(entry.getValue())));
                }
            }else if(clave.startsWith("__BOOL__")){
                subCriteria.add(Restrictions.eq(clave.substring(8), Boolean.parseBoolean(entry.getValue())));
            }else if(clave.startsWith("__DATE__FROM__")){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                try {
                    subCriteria.add(Restrictions.ge(clave.substring(14), sdf.parse(entry.getValue())));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(clave.startsWith("__DATE__UNTIL__")){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                try {
                    subCriteria.add(Restrictions.le(clave.substring(15), sdf.parse(entry.getValue())));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(clave.startsWith("__INT__FROM__")){
                subCriteria.add(Restrictions.ge(clave.substring(13), Integer.parseInt(entry.getValue())));
            }else if(clave.startsWith("__INT__UNTIL__")){
                subCriteria.add(Restrictions.le(clave.substring(14), Integer.parseInt(entry.getValue())));
            }else{
                if(entry.getValue().startsWith("{") && entry.getValue().endsWith("}")){
                    subCriteria.add(Restrictions.in(clave, entry.getValue().replace("{", "").replace("}", "").split(",")));
                }else if(!entry.getValue().contains("%")){
                	 if(entry.getValue().equalsIgnoreCase("null")){
                         subCriteria.add(Restrictions.isNull(clave));
                     }else{
                         try{
                             subCriteria.add(Restrictions.eq(clave, Integer.parseInt(entry.getValue())));
                         }catch(NumberFormatException e){
                             subCriteria.add(Restrictions.eq(clave, entry.getValue()));
                         }
                     }
                } 
                else{
                    subCriteria.add(Restrictions.ilike(clave, entry.getValue()));
                }
            }
        }
    } 
    
    private DetachedCriteria getCriteria(DetachedCriteria criteria,String key,Map<String, DetachedCriteria> mapaCriterios) {
        String[] split = key.split(PUNTO_ESCAPADO);
        if(mapaCriterios==null || split.length<2){
            return criteria;
        }else{
            return mapaCriterios.get(mergeStrings(split, 0, split.length-2));
        }
            
    }

    protected void addOrder(DetachedCriteria criteria, String sort,Map<String, DetachedCriteria> mapaCriterios) {
        if (sort != null) {
            String innerSort =sort;
            if(innerSort.contains("&&")){
                if(innerSort.charAt(0)=='-'){
                    innerSort = innerSort.replace("&&", ",-");
                }else{
                    innerSort = innerSort.replace("&&", ",+");
                }
            }
            String[] sortFields = innerSort.split(",");
            for (int i = 0; i < sortFields.length; i++) {
                internalAddOrder(sortFields,i,criteria,mapaCriterios);
            }
        }
    }
    
    private void internalAddOrder(String[] sortFields, int i, DetachedCriteria criteria, Map<String, DetachedCriteria> mapaCriterios){
        char direction = sortFields[i].charAt(0);
        String field = sortFields[i].substring(1);
        DetachedCriteria subCriteria = getCriteria(criteria, field, mapaCriterios);
        if (direction == '+' || direction == ' ') {
            String[] split = field.split(PUNTO_ESCAPADO);
            if(split.length<2 || mapaCriterios==null){
                subCriteria.addOrder(Order.asc(field));
            }else{
                subCriteria.addOrder(Order.asc(split[split.length-1]));
            }
        } else if (direction == '-') {
            String[] split = field.split(PUNTO_ESCAPADO);
            if(split.length<2 || mapaCriterios==null){
                subCriteria.addOrder(Order.desc(field));
            }else{
                subCriteria.addOrder(Order.desc(split[split.length-1]));
            }
        }
    }
    protected void addOrder(DetachedCriteria criteria, String sort){
        this.addOrder(criteria, sort,null);
    }

    protected void addRestrictionsBuscar(DetachedCriteria criteria, Map<String, String> constraints) {
        List<String> claves = new ArrayList<String>();
        
        for (Map.Entry<String, String> entry: constraints.entrySet()) {

            if(entry.getKey().contains(".")){
                String[] split = entry.getKey().split("\\.");
                
                String propertyName = split[0];
                String aliasName = split[0];
                
                if(!claves.contains(propertyName)) {
                    //System.out.println("Alias => " + propertyName);
                    criteria.createAlias(propertyName, aliasName);
                    claves.add(propertyName);
                }
            }
            if(entry.getKey().contains("id")){
                if(entry.getValue().startsWith(NOT_LLAVE) && entry.getValue().endsWith("}")){
                    criteria.add(Restrictions.ne(entry.getKey(), Integer.parseInt(entry.getValue().replace(NOT_LLAVE, "").replace("}", ""))));
                }
                else{
                    criteria.add(Restrictions.eq(entry.getKey(), Integer.parseInt(entry.getValue())));
                }
            } else {
                addRestrictionsBuscarEspecifico(entry,criteria);
            } 
        }
    }

    protected void addRestrictionsBuscarEspecifico(Entry<String, String> entry, DetachedCriteria criteria) {
        if(entry.getValue().startsWith("{") && entry.getValue().endsWith("}")){
            criteria.add(Restrictions.in(entry.getKey(), entry.getValue().replace("{", "").replace("}", "").split(",")));
        } else if (entry.getKey().contains("fecha-inicial")) {
            
            //System.out.println("Fecha incial => " + entry.getValue() );
            
            java.util.Date fechaInicialAuxs = null;
            String strFecha = entry.getValue();
            try {
                fechaInicialAuxs = DATE_FORMAT.parse(strFecha);
                Date fechaIncio = new Date(fechaInicialAuxs.getTime());
                criteria.add( Restrictions.ge("fecCreacion", fechaIncio) );
            } catch (java.text.ParseException e) {
                logger.error(e.getLocalizedMessage());
            }
        }else if (entry.getKey().contains("fecha-final")) {
            //System.out.println("Fecha final => " + entry.getValue() );
            
            java.util.Date fechaFinalAuxs = null;
            String strFecha = entry.getValue();
            try {
                fechaFinalAuxs = DATE_FORMAT.parse(strFecha);
                Date fechaFinal = new Date(fechaFinalAuxs.getTime());
                criteria.add( Restrictions.le("fecCreacion", fechaFinal) );
            } catch (java.text.ParseException e) {
                logger.error(e.getLocalizedMessage());
            } 
            
        } else if ( entry.getKey().contains("bool") ) {
            String[] split = entry.getKey().split("\\-");
            //System.out.println("Propiedad => " + split[1] + " / " + Boolean.valueOf(entry.getValue().toString()));
            criteria.add( Restrictions.eq( split[1], Boolean.valueOf(entry.getValue() ) ));
        } else if ( entry.getKey().equals("BUSCAR")) {
            ; // lo ignora
        } else{
            criteria.add(Restrictions.ilike(entry.getKey(), entry.getValue()));
        }
    }
    private void creaCriteriasUnitarias(Entry<String, String> entry, Map<String, DetachedCriteria> mapaCriterios, DetachedCriteria criteria){
        String[] split = entry.getKey().split(PUNTO_ESCAPADO);
        if(split.length>1){
            incluirCriterias(split,mapaCriterios,criteria);
        }
    }
    private void anyadirOrdenUnitario(String orden, Map<String,DetachedCriteria> mapaCriterios, DetachedCriteria criteria){
        String[] split = orden.split(PUNTO_ESCAPADO);
        incluirCriterias(split,mapaCriterios,criteria);
    }
    private void incluirCriterias(String[] split, Map<String, DetachedCriteria> mapaCriterios, DetachedCriteria criteria){
        for(int i=0;i<split.length-1;i++){
            if(!mapaCriterios.containsKey(mergeStrings(split, 0, i))){
                if(i==0){
                    mapaCriterios.put(split[i], criteria.createCriteria(split[i]));
                }else{
                    mapaCriterios.put(mergeStrings(split,0,i), mapaCriterios.get(mergeStrings(split,0,i-1)).createCriteria(split[i]));
                }
            }
        }
    }
}
