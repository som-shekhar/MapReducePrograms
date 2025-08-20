package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class TFMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	IntWritable one = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// Converting the value from the data type text to string, so that we can use string functions
				String line = value.toString();
				
				// breaking the line into individual words using the regular expression \\W+
				String[] words = line.split("\\W+");
				
				// Iterate each of the words 
				//Ouptut key = word and output value = 1
				String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
				for(String word : words) {
					if(!word.isEmpty()) {
						context.write(new Text(word +":"+fileName), one);
					}
					
				}
	}

}
