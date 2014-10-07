package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser{
	private String programName;
	private Vector<ArgumentValues> argumentList; 
	private boolean helpOut = false;
	private boolean tooManyArg = false;
	private boolean tooFewArg = false;
	private ArgumentValues invalidArg;
	private String missingArg;
	
	public ArgumentParser(){
		argumentList = new Vector<ArgumentValues>();
	}
	
	public void addArgument(String argumentName){
		argumentList.add(new ArgumentValues(argumentName));
	}
	
	public void addArgument(String argumentName, String argumentDescription){
		argumentList.add(new ArgumentValues(argumentName, argumentDescription));
	}
	
	public int getNumArguments(){
		return argumentList.size();
	}
	
	public void parse(String s){
		Scanner scan = new Scanner(s);
		programName = scan.next();
		String nextVal = "";
		boolean loop = true;
		int i=0;
		while(loop){
			if(scan.hasNext()){
				if(getNumArguments()<i+1){
					tooManyArg=true;
					String a =scan.next();
					while(scan.hasNext()){
					a=a+" "+scan.next();
					}
					invalidArg = new ArgumentValues("invalid");
					invalidArg.setValue(a);
					loop = false;
					
				}
				else{
					nextVal = scan.next();
					if(nextVal.equals("-h")){
						helpOut = true;
						loop = false;
					}
					else {
						argumentList.get(i).setValue(nextVal);
						i++;
					}
				}
			}
			else loop = false;
		}	
		if(i<getNumArguments()){
			tooFewArg=true;
			missingArg=argumentList.get(i).getName();
			for(i=i+1;i<getNumArguments();i++){
				missingArg=missingArg+" "+argumentList.get(i).getName();
			}
		}
	}
	
	public boolean getHelpOut(){
		return helpOut;
	}
	
	public ArgumentValues getArgument(String argName){
		ArgumentValues argument;
		for(int i=0;i<getNumArguments();i++){
			argument = argumentList.get(i);
			if(argument.getName().equals(argName)){
				return argument;
			}
		}
		ArgumentValues val =new ArgumentValues("");
		val.setValue("");
		return val;
	}
	
	public String getUsage(){
		String s = programName.toString()+"/n positional arguments:";
		for(int i=0;i<getNumArguments();i++){
			s=s+"/n "+argumentList.get(i).getName();
			if(argumentList.get(i).getDescription()!=null){
				s=s+" "+argumentList.get(i).getDescription();
			}
		}
		return s;
	}
	
	public String getUnknownValue(){
		return invalidArg.getValue();
	}
	
	public String getUnknownArg(){
		return missingArg;
	}
	
	public String missingArguments(){
		return getUsage()+"/n error: the following arguments are required: "+getUnknownArg();
		
	}
	
	public String tooManyArguments(){
		return getUsage()+"/n error: unrecognised arguments: "+getUnknownValue();
	}
	
	public boolean checkTooManyArg(){return tooManyArg;}
	public boolean checkTooFewArg(){return tooFewArg;}
}