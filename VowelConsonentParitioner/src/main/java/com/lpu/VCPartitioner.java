package com.lpu;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class VCPartitioner extends Partitioner<Text, Text>{

	@Override
	public int getPartition(Text key, Text value, int numReducer) {
		char[] vowels = {'a','e','i','o','u'};
		char firstChar = key.toString().toLowerCase().charAt(0);
		boolean contains = false;
		for( char c: vowels) {
			if (c==firstChar) {
				contains=true;
				break;
			}
		}
		if(contains) {
			//the word is starting with vowels.,
			return 0;
		}
		else {
			return 1;
		}
	}

}
