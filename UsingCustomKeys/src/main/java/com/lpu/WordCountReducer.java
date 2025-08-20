package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<CustomKey, CustomValue, Text, IntWritable> {

	@Override
	protected void reduce(CustomKey word, Iterable<CustomValue> values,
			Reducer<CustomKey, CustomValue, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		//output key = word and
	    // outputvalue =sum =5
			
			// initializing the sum as =
			int sum=0;
			//iterate over the list of values
			for(CustomValue val :values) {
				//incrementing the sum
				//val.get() will be converted into int
				sum = sum + val.getValue();
			}
			
			context.write(new Text(word.getWord()), new IntWritable(sum));
			
		}


		
	}

