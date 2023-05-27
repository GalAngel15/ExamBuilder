package Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;


public class MyArrayList {
	private int max = 10;
	private Test[] a = new Test[max];
	private int count = 0;
	private Set<Observer> set = new HashSet<>();

	public void add(Test value) {
		if(count == a.length) {
			a = Arrays.copyOf(a, max*2);
			max *= 2;
		}
		a[count++] = value;
	}
	
	public Iterator<Test> iterator() {
		return new ConcreteIterator(); 
	}
	
	public void attach(Observer o) {
		set.add(o);
	}
	
	public void click() {
		myNotify();
	}

	public void detach(Observer o) {
		set.remove(o);
	}

	public void myNotify() {
		for (Observer o : set)
			o.update();
	}
	
	private class ConcreteIterator implements Iterator<Test> {
		private int cur = 0;
		private boolean isUsedNext = false;
		
		
		@Override
		public boolean hasNext() {
			return cur < count;
		}

		@Override
		public Test next() {
			if(!hasNext())
				throw new NoSuchElementException();
			isUsedNext = true;
			return a[cur++];
		}
		@Override
		public void remove() {
			if(!isUsedNext) 
				throw new IllegalStateException();
			for (int i = cur-1; i < count - 1; i++) 
			      a[i] = a[i + 1];
			count--;
			cur--;
			isUsedNext = false;
		}
	}
		
}
