package fr.Group13.CLUIClasses;

import fr.Group13.DesignPatternClasses.*;
import fr.Group13.MainVelibClasses.*;

public class Setup implements Command {

	public Setup() {}
	
	@Override
	public void processArguments(String arguments[], MainCLUI cli) {
		if (arguments.length == 1) {
			this.command(arguments, cli, 1);
		} else if (arguments.length == 5) {
			String args[] = arguments;
			int stations=0;
			int slots=0;
			double side=0;
			double bikes=0;
			try {
			   stations = Integer.parseInt(args[1]);
			   slots = Integer.parseInt(args[2]);
			   side = Double.parseDouble(args[3]);
			   bikes =Double.parseDouble(args[4]);
			   
			   if (slots < stations) System.out.println("slot is incorrect, must be greater than number of station, please retry.");
			   else if (bikes > 1) System.out.println("bik proportion must be below 1, please retry.");
			   else if (bikes <= 0 || slots < 0 || stations <=0 || side <=0) System.out.println("parameters must be positive, please retry");
			   else command(args, cli, 2);
			}
			catch (NumberFormatException e)
			{
			   System.out.println("command will be ignored \n one parameter wasn't identified as a number, please retry.");
			}
		} else {System.out.println("Number of argument is incorrect, please retry");}

	}

	@Override
	public void command(String[] args, MainCLUI cli, int configuration) {
		MyVelibFactory factory = new MyVelibFactory();
		switch (configuration) {
			case 1 :
				cli.systems.add(factory.systemCreation(args[0], 10, 10, 4, 0.75));
				System.out.println("Succesfully created MyVelib system of name " + args[0]);
				break;
			case 2 :
				cli.systems.add(factory.systemCreation(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4])));
				System.out.println("Succesfully created MyVelib system of name " + args[0] + " with given parameters");
				break;
		}

	}

}
