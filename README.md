# How to import the project into your eclipse

Step 1: Go inside the project (Example: WordCount)

Step 2: Run the command mvn eclipse:clean && mvn eclipse:eclipse

Step 3: mvn clean install

The above command will create a target folder inside the project and inside the jar will be present. You need to use this jar to run the MR job


# Command to run MapReduce Job

hadoop jar <the name of the jar> <Fully qualified driver classname> <input_dir_on_hdfs> <op_dir>

NOTE: op_dir will be automatically created as part of job run.

Example:

hadoop jar target/word_count.jar com.lpu.WordcountDriver shakespeare wordcount


