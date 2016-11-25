package net.atos.common.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;


/**
 * Bean para encapsular consultas paginadas en base de datos en las que además haga falta
 * conocer el número total de registros que devolvería sin paginación.
 */
public class QueryList<E> implements List<E>, RandomAccess, Cloneable, Serializable
 {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2231254294651281832L;
	
	/** Número real de filas que devolvería la query sin paginación. */
	long totalCount =0;
	
	/** Lista de resultados de la query. Datos de la página actual. */
	List<E> resultList;
	
	public QueryList(){
		setResultList(new ArrayList<E>());
		totalCount=0;
	}
	
//	public QueryList(List<E> lst, Integer totalCount){
//		setResultList(lst);
//		setTotalCount(totalCount.longValue());
//	}
	public QueryList(List<E> lst, Long totalCount){
		setResultList(lst);
		setTotalCount(totalCount);
	}
	
	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int index, E element) {
		resultList.add(index, element);
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(E o) {
		return resultList.add(o);
	}


	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	public void clear() {
		resultList.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return resultList.contains(o);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return resultList.equals(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	public E get(int index) {
		return resultList.get(index);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return resultList.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return resultList.indexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty() {
		return resultList.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	public Iterator<E> iterator() {
		return resultList.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		return resultList.lastIndexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	public ListIterator<E> listIterator() {
		return resultList.listIterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator<E> listIterator(int index) {
		return resultList.listIterator(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	public E remove(int index) {
		return resultList.remove(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return resultList.remove(o);
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public E set(int index, E element) {
		return resultList.set(index, element);
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	public int size() {
		return resultList.size();
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	public List<E> subList(int fromIndex, int toIndex) {
		return resultList.subList(fromIndex, toIndex);
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray() {
		return resultList.toArray();
	}



	/**
	 * Gets the result list.
	 * 
	 * @return the result list
	 */
	public List<E> getResultList() {
		return resultList;
	}
	
	/**
	 * Sets the result list.
	 * 
	 * @param resultList the new result list
	 */
	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends E> c) {
		return resultList.addAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		return resultList.addAll(index, c);
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return resultList.containsAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return resultList.containsAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return resultList.retainAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(T[])
	 */
	public <T> T[] toArray(T[] a) {
		return toArray(a);
	}

	/**
	 * Obtiene el número real de registros de la query.
	 * 
	 * @return Número real de retgistros de la query.
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * Establece el número real de registros de la query.
	 * 
	 * @param totalCount Número real de registros de la query.
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<E> getList(){
		return getResultList();
	}
}
