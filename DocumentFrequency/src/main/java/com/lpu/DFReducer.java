package com.lpu;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DFReducer extends Reducer<Text, Text, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<Text> filenames, Reducer<Text, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		Set<String> uniqueFileList = new HashSet<String>();

		for (Text file : filenames) {
			uniqueFileList.add(file.toString());
		}

		// thelist of filenames i woud like to pass as comma separated
		context.write(key, new IntWritable(uniqueFileList.size()));
	}

}
