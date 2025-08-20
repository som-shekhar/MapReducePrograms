package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TFReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text word, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

		// output key = word and
		// outputvalue =sum =5

		// initializing the sum as =
		int sum = 0;
		// iterate over the list of values
		for (IntWritable val : values) {
			// incrementing the sum
			// val.get() will be converted into int
			sum = sum + val.get();
		}

		context.write(word, new IntWritable(sum));

	}

}
