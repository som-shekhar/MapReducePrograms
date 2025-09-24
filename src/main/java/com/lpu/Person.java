package com.lpu;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Person implements WritableComparable<Person> {
	
	private Text fName;
	
	private Text lName;
	
	public Text getfName() {
		return fName;
	}
	public void setfName(Text fName) {
		this.fName = fName;
	}
	public Text getlName() {
		return lName;
	}
	public void setlName(Text lName) {
		this.lName = lName;
	}

	

	public Person(){
		fName = new Text();
		lName = new Text();
	}
	public Person(Text fName, Text lName) {
		super();
		this.fName = new Text(fName);;
		this.lName = new Text(lName);;
	}

	@Override
	public String toString() {
		return "Person [fName=" + fName + ", lName=" + lName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.lName.readFields(in);
		this.fName.readFields(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.lName.write(out);
		this.fName.write(out);
		
	}

	/**
	 * We want to sort the last names and with in the last name we want to sort w.r.t to their first
	 * name
	 * 
	 * p1: lname: smith fname: john
	 * 
	 * p2: lname: smith fname: bob
	 * 
	 * cmp -1 0 1
	 */
	@Override
	public int compareTo(Person other) {
		int cmp = this.getlName().compareTo(other.getlName());
		if(cmp == 0){
			cmp = this.fName.compareTo(other.getfName());
		}
		return cmp;
	}

}
