package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AverageWordLengthReducer extends Reducer<Text, IntWritable, Text, FloatWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, FloatWritable>.Context context) throws IOException, InterruptedException {
		
		int count=0;
		int sum=0;
		
		for(IntWritable val : values) {
			sum = sum + val.get();
			count += 1;
		}
		FloatWritable avg = new FloatWritable((float)sum/count);
		context.write(key, avg);
		
	}
	
	

}
