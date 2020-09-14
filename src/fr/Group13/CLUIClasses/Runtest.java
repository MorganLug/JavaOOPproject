package fr.Group13.CLUIClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

public class Runtest implements Command {

	@Override
	public void processArguments(String[] arguments, MainCLUI cli) {
		FileReader file = null;
		BufferedReader reader = null;
		try {
			file = new FileReader(arguments[0]);
			reader = new BufferedReader(file);
			command(arguments,cli,1);
		} catch (Exception e) {
			System.out.println("File couldn't be found, please retry");;
		} finally {
			if (reader != null) {
				try {reader.close();}
				catch (IOException e) {System.out.println("There was a problem with the file, please retry.");}
			}
		}
		
	}

	@Override
	public void command(String[] args, MainCLUI cli, int configuration) {
		FileReader file = null;
		BufferedReader reader = null;
		try {
			file = new FileReader(args[0]);
			reader = new BufferedReader(file);
			String line = "";
			CommandProcessing processor = new CommandProcessing();
			while ((line = reader.readLine()) != null) {
				processor.identifyCommand(line, cli);
			}		
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {reader.close();}
				catch (IOException e) {}
			}
		}
		
	}
	
	public void launchTest(String testScenarioName) {
		MainCLUI cli = new MainCLUI();
		CommandProcessing processor = new CommandProcessing();
		
		String filepath = new File("").getAbsolutePath();
		filepath += "\\eval\\"+testScenarioName;
		
		processor.identifyCommand("runtest " + filepath, cli);
		
		System.out.println("\t Finished running test of file "+testScenarioName + " located "+filepath);
	}
	
	public void launchTest(String testScenarioName, MainCLUI cli) {
		CommandProcessing processor = new CommandProcessing();
		
		String filepath = new File("").getAbsolutePath();
		filepath += "\\eval\\"+testScenarioName;
		
		processor.identifyCommand("runtest " + filepath, cli);
		
		System.out.println("\t Finished running test of file "+testScenarioName + " located "+filepath);
	}
}
