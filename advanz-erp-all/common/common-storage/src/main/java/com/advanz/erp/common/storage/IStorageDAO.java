package com.advanz.erp.common.storage;

public interface IStorageDAO<T> {
	public void create(T entity);
	public T read(Integer id);
	public T update(T entity);
	public void delete(T entity);
	public static final String ACCESS_PATTERN = "JPA";
}
