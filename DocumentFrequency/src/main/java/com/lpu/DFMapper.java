package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class DFMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		String[] words = value.toString().split("\\W+");
		String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
		if (words.length > 0) {
			for (String word : words) {
				if(!word.isEmpty()) {
				context.write(new Text(word), new Text(fileName));
				}
			}
		}

	}
}
