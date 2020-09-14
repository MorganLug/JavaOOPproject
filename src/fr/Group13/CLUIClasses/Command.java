package fr.Group13.CLUIClasses;

public interface Command {
	public void processArguments(String[] arguments, MainCLUI cli);
	public void command(String[] args, MainCLUI cli, int configuration);
}
