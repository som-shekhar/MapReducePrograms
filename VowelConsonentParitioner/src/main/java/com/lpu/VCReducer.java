package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class VCReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text word, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
							context.write(word, new Text(""));
			
		}


		
	}

