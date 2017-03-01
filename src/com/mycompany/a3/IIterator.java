package com.mycompany.a3;

public interface IIterator<E> {
	public E getNext();
	public boolean hasNext();
	public E remove();
	public E elementAt(int n);
	public int size();
}
