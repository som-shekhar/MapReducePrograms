package com.lpu;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * THis comparator is used for group by key operation
 * Obejective was that the lastname must be grouped toether
 */
public class PersonGroupingComparator extends WritableComparator {
	public PersonGroupingComparator(){
		super(Person.class,true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		Person p1 = (Person) a;
		Person p2 = (Person) b;
		//we are grouping or comparing with the last names
		int cmp = p1.getlName().compareTo(p2.getlName());
		
		return cmp;
	}
}
