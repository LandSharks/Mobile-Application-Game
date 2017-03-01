package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection{
	private Vector v;
	public GameObjectCollection() {
		v = new Vector();
	}
	public void add(Object o) {
		v.add(o);
	}
	public IIterator getIterator() {
		return new GIterator();
	}
	private class GIterator implements IIterator{
		private int currIndex;
		public GIterator() {
			currIndex = -1;
		}
		public Object getNext() {
			currIndex++;
			try {
				return v.elementAt(currIndex);
			} catch (Exception e) {
				return null;
			}
		}
		public boolean hasNext() {
			if(v.size() <= 0 || currIndex == v.size()-1) {
				return false;
			} else {
				return true;
			}
		}
		public Object remove() {
			GameObject o = (GameObject)v.remove(currIndex);
			currIndex--;
			return o;
		}
		public int size() {
			return v.size();
		}
		public Object elementAt(int n) {
			return v.elementAt(n);
		}
	}
}
