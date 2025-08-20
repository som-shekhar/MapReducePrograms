package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, CustomKey, CustomValue> {
	



	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, CustomKey, CustomValue>.Context context)
			throws IOException, InterruptedException {
		// Converting the value from the data type text to string, so that we can use string functions
				String line = value.toString();
				
				// breaking the line into individual words using the regular expression \\W+
				String[] words = line.split("\\W+");
				
				// Iterate each of the words 
				//Ouptut key = word and output value = 1
				
				for(String word : words) {
					if(!word.isEmpty()) {
						CustomKey outputKey = new CustomKey(word);
					    context.write(outputKey, new CustomValue());
					}
					
				}
	}

}
