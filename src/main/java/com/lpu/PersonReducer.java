package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PersonReducer extends Reducer<Person, Text, Text, Text> {

	@Override
	protected void reduce(Person key, Iterable<Text> values, 
			Reducer<Person, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		for(Text fname:values){
			context.write(key.getlName(),fname);
		}
	}

}
