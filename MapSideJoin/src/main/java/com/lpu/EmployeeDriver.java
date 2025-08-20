package com.lpu;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class EmployeeDriver extends Configured implements Tool  {
	
	

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new EmployeeDriver(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		
		Job job = Job.getInstance(getConf(), "word count");
		//Job job = new Job(getConf(),"Basic Word Count Job");
		
		// You need to specify the main class in the below methods
		job.setJarByClass(EmployeeDriver.class);

		job.setMapperClass(EmployeeMapper.class);
		
			
		job.setInputFormatClass(TextInputFormat.class);
			
		//map outputkey and outpyt value class
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setNumReduceTasks(0);
		
		//this needs to be used instead of distributed cache  class , as that has been deprecated
	    job.addCacheFile(new URI("hdfs:///user/shekhar/id_data"));
		
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//blocking and non blocking operation
		//blocking operation: The flow or the programm exeuction stops and wait till its completing
		//Non blocking operation: the job is submitted to the cluster and the program finishes
		job.waitForCompletion(true);
		return 0;
	}

}
