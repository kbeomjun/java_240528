package program;

public interface Program {
	
	void printMenu();
	
	void runMenu(int menu) throws Exception;

	void run();
	
	void save(String fileName);
	
	void load(String fileName);
	
}