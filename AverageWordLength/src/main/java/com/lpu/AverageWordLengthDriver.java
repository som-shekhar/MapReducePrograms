package com.lpu;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AverageWordLengthDriver {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "word count");
		//Job job = new Job(getConf(),"Basic Word Count Job");
		
		// You need to specify the main class in the below methods
		job.setJarByClass(AverageWordLengthDriver.class);

		job.setMapperClass(AverageWordLengthMapper.class);
		job.setReducerClass(AverageWordLengthReducer.class);
			
		job.setInputFormatClass(TextInputFormat.class);
			
		//map outputkey and outpyt value class
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// setting the number of reducers to 2
		job.setNumReduceTasks(1);
		
		//reduce output key and output value cass
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//blocking and non blocking operation
		//blocking operation: The flow or the programm exeuction stops and wait till its completing
		//Non blocking operation: the job is submitted to the cluster and the program finishes
		job.waitForCompletion(true);
	}

}
