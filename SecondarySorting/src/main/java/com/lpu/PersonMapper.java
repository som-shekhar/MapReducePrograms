package com.lpu;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
	//								mapper<fname, lname,
public class PersonMapper extends Mapper<Text,Text,Person,Text> {
	Person opKey = new Person();
	
	//Lastname is my key becuase i want to group them together

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Person, Text>.Context context)
			throws IOException, InterruptedException {
		opKey.setfName(key);
		opKey.setlName(value);
		
		context.write(opKey, value);
	}

}

//i want to group all the same lnames together and while writing teh fname i want the fname to be sortd


/*
 * 
 * smith bob
 * smith david
 * smith john
 */
