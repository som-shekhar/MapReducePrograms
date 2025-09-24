package com.lpu;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


/**
 * Since our key is lastname, we want to ensure that all the keys having the same lastname
 * must end up in the same reducer
 */

public class PersonPartitioner extends Partitioner<Person,Text> {

	@Override
	public int getPartition(Person key, Text value, int numReducer) {
		
		return (key.getlName().hashCode() & Integer.MAX_VALUE) % numReducer;
		/*
		 * YOu are taking the hash code and doing the bitwise operation with the maximum value 
		 * of integer, so to covert the negative hashcode to positive hashcodes
		 * 
		 */
	}

}
