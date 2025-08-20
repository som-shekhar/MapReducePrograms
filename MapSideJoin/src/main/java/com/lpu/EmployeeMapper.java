package com.lpu;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmployeeMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	//key is id and value is name
	HashMap<Integer, String> idMap = new HashMap<Integer, String>();

	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
	}

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//reading the cache and lookupdate
		Path[] localPath = context.getLocalCacheFiles();
		//since we have cached singel file in teh ddriver code, so we use index 0
		Path IdDataPath = localPath[0];
		// we have read the file adn populate the hashmap
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(IdDataPath.toString()))));
		
		String line;
		line = reader.readLine();
		while (line != null) {
			String[] splits = line.split(",");
			idMap.put(Integer.parseInt(splits[0]), splits[1]);
			line = reader.readLine();
			
		}
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		 String empRecord = value.toString();
		 String[] splits = empRecord.split(",");
		 //replacing the id with the name
		 String name = idMap.get(Integer.parseInt(splits[0]));
		 
		 String updatedName = name+","+splits[1]+","+splits[2];
		 context.write(new Text(updatedName), new Text(""));
		 
		
	}

}
