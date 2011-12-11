package ch.boxi.pictureStatistic.data;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Constraint {
	private String fieldName;
	private Set<String> isInList;
	
	public Constraint(String fieldName){
		this.fieldName = fieldName;
		isInList = new TreeSet<String>();
	}
	
	public String getFieldName(){
		return fieldName;
	}
	
	public Set<String> getIsInList(){
		return Collections.unmodifiableSet(isInList);
	}
	
	public void addInListItem(String item){
		isInList.add(item);
	}
	
	public void setIsInList(Set<String> newList){
		isInList = newList;
	}
	
	public String getSqlConstraint(){
		StringBuffer sb = new StringBuffer();
		sb.append(fieldName);
		sb.append(" IN (");
		Iterator<String> itr = isInList.iterator();
		while(itr.hasNext()){
			String item = itr.next();
			sb.append("'");
			sb.append(item);
			sb.append("'");
			if(itr.hasNext()){
				sb.append(", ");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	public boolean hasAConstraint(){
		return isInList.size() > 0;
	}
}
