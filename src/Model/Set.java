package Model;

import java.util.ArrayList;
import java.io.Serializable;


public class Set<T extends Answer> implements Cloneable,Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<T> setList;
	
	public Set() {
		 this.setList = new ArrayList<T>();
	}
	
	public boolean add(T variable) {
		for(int i = 0; i < setList.size(); i++) {
			if(setList.get(i).getAnsText().equals(variable.getAnsText()))
				return false;
		}
		setList.add(variable);
		return true;	
	}
	
	public void addAll(ArrayList <T> ans) {
		for(int i = 0; i < ans.size(); i++) {
			add(ans.get(i));
		}
	}
	
	public int size() {
		return this.setList.size();
	}
	
	public T get(int index) {
		return setList.get(index);
	}
	
	public T set(int index, T variable) {
		return setList.set(index, variable);
	}
	
	public T remove(int index) {
		return setList.remove(index);
	}
	
	public boolean contains(T variable) {
		return setList.contains(variable);
	}
	
	public String toString() {
		return setList.toString();
	}

	
	@Override
	public Set<T> clone() throws CloneNotSupportedException{
		Set<T> temp = (Set<T>) super.clone();
		temp.setList = (ArrayList) setList.clone();
 		return temp;
	}
	
}
