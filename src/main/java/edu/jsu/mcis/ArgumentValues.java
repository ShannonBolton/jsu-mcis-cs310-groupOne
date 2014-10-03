package edu.jsu.mcis;
/* 
 * Create a class called ArgumentValues that can hold named,string values 
 * representing positional arguments.
 *
 */
import java.util.*;
//Date Created 9/30/2014

public class ArgumentValues{

	private String argument;
	private String value;
	
	
	public ArgumentValues(String arg){
		argument = arg;
		value = "";
	}
	/* This object is used to hold positional arguments while associating
	 * values with it. Overall it should be created and stored in a 
	 * Vector<ArgumentValues> somewhere in the ArgumentParser.class so 
	 * it can be easily accessed later through sorting.
	 */
	 
	 public String getValue(){
		return value;
	 }
	 public String getName(){
		return argument;
	 }
	 public void setValue(String v){
		value = v;
	 }

}