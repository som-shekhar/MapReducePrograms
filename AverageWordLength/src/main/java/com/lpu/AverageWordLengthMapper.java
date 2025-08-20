package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageWordLengthMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		//conver the valye from Text to String
		String line = value.toString();
		//split the line into individual words
		String[] splits = line.split("\\W+");
		
		for (String word: splits) {
			//ingore the spaces
			if(word.length() > 0) {
				//take the first char
				char firstChar = word.charAt(0);
				//filter if the first char is digit
				if(!Character.isDigit(firstChar)) {
					Text opkey = new Text(Character.toString(firstChar).toUpperCase());
					IntWritable opValue = new IntWritable(word.length());
					context.write(opkey, opValue);
				}
				
			}
		}
		
	}
	

}
