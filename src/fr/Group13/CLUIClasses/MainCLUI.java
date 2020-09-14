package fr.Group13.CLUIClasses;

import java.util.ArrayList;

import java.util.Scanner;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

public class MainCLUI {
 
	public boolean active = true;
	public ArrayList<MyVelib> systems = new ArrayList<MyVelib>();
	
	public static void main(String[] args) {
		MainCLUI cli = Runini.launchIni("my_velib.ini");
		CommandProcessing processor = new CommandProcessing();
		System.out.println("\t Welcome on MyVelib CLUI. You can type in commands in the following form : \n commandname arg1 arg2 ... argn");
		System.out.println("If you want to exit it, type exit.");
		Scanner in = new Scanner(System.in);
		while (cli.active) {
	        String command = in.nextLine();
	        processor.identifyCommand(command, cli);
		}
	}
	
	public MyVelib getSystem(int index) {
		return systems.get(index);
	}
}
