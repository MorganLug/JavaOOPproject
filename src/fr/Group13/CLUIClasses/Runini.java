package fr.Group13.CLUIClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

public class Runini {

	public void command(String filepath, MainCLUI cli) {
		FileReader file = null;
		BufferedReader reader = null;
		try {
			file = new FileReader(filepath);
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
	
	public static MainCLUI launchIni(String iniConfigFileName) {
		MainCLUI cli = new MainCLUI();
		CommandProcessing processor = new CommandProcessing();
		Runini runini = new Runini();
		
		String filepath = new File("").getAbsolutePath();
		filepath += "\\eval\\"+iniConfigFileName;
		
		runini.command(filepath, cli);
		
		System.out.println("\n\t Finished initialization of CLUI, \"default\" system has been loaded ");
		return cli;
	}
}
