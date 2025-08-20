package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	
	//member variable
	private IntWritable one ;
	String searchWord;

	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
	}

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//initialziing the member variable only once
		this.one = new IntWritable(1);
		
		String searchWord = context.getConfiguration().get("WORD");
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// Converting the value from the data type text to string, so that we can use string functions
				String line = value.toString();
				
				// breaking the line into individual words using the regular expression \\W+
				String[] words = line.split("\\W+");
				
				// Iterate each of the words 
				//Ouptut key = word and output value = 1
				
				for(String word : words) {
					if(!word.isEmpty() && word.equals(this.searchWord)) {
					Text outputKey = new Text(word);
					context.write(outputKey, one);
					}
					
				}
	}

}
