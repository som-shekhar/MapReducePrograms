package com.lpu;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class PersonDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(PersonDriver.class);
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setMapperClass(PersonMapper.class);
		job.setReducerClass(PersonReducer.class);
		
		job.setMapOutputKeyClass(Person.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setPartitionerClass(PersonPartitioner.class);
		job.setSortComparatorClass(PersonSortComparator.class);
		job.setGroupingComparatorClass(PersonGroupingComparator.class);
		
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		job.waitForCompletion(true);
		
		
		return 0;
	}
	
	
	public static void main(String[] args) throws Exception{
		ToolRunner.run(new PersonDriver(), args);
	}

}
