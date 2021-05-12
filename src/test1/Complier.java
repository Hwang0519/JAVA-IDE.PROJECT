package test1;

public class Complier {
	public static void main(String[] args) {
		Cmd cmd = new Cmd();
	
		String command = cmd.inputCommand("cd C:\\Users\\phil5\\eclipse-workspace\\Myjava\\src\\test1 && javac main.java");
		String result = cmd.execCommand(command);
		
		System.out.println(result);
		System.out.printf(" result ");
	}
}