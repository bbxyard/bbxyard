package com.bbxyard.app;

import java.awt.JobAttributes;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;





/**
 * Hello world!
 *
 */
public class App 
{
	public static void testRW() throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create("/tmp"), conf);
		Path path = new Path("file:///tmp/");	
		FileStatus[] fileStatus = fs.listStatus(path);
		
	}
	
    public static void main( String[] args ) throws IOException
    {
    	testRW();
        System.out.println( "Hello World!" );
    }
}