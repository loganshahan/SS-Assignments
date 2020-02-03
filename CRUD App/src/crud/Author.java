/**
 * 
 */
package crud;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.HashMap;  

public class Author {
	
	//global variables
	int authorID;
	String authorName;
	static int nextID;
	static String fileName = "resources/lms/AuthorsTestTwo.txt";
	static HashMap<Integer, Author> mapA = new HashMap<Integer, Author>();
	
	public Author(int authorID, String authorName) {
		this.authorID = authorID;
		this.authorName = authorName;
		
	}

	
	public static void menu() {
		//initial menu for author
		
		System.out.println("\n" + "Select 1 to add Authors");
		System.out.println("Select 2 to see all Authors");
		System.out.println("Select 3 to update Author");
		System.out.println("Select 4 to delete Author");
		System.out.println("Select 5 to return to previous menu");
			
		//make sure option is 1-5
		int option = Main.validInt(5);
		switch(option) {
		case 1:
			add();
			break;
		case 2:
			read();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
		case 5:
			Main.menu();
		default:
			Author.menu();
		}
		
	}

	
	public static void read() {
		//read from file
		
		readAuthors();
		Author.menu();
	}
	
	public static void readAuthors() {
		//read from file
		try(BufferedReader bufStream = new BufferedReader (new FileReader(fileName))){
			String line = bufStream.readLine();
			while(line!=null) {
				int id = Integer.parseInt(line.substring(0, line.indexOf(",")));
				String name = line.substring(line.indexOf(",")+1, line.length());
				System.out.println("Author Name: "+name+" with ID: "+id);
				line = bufStream.readLine();
				
			}
			
		}catch(Exception e) {
			System.out.println("Authors are empty");
		}
	}
	
	public static void add() {
		
		addAuthors();
		Author.menu();
		
		
	}
	public static void addAuthors() {
		String userInput;
		int id = 0;
		String name = null;
		System.out.println("Add new author");
		userInput = Main.sc.nextLine();
		try(FileWriter filewriter = new FileWriter(new File(fileName),true)){
			//increment id and add userinput for name
			id = nextID;
			name = userInput;
			filewriter.write(nextID + "," +userInput + "\n");
			nextID++;
			
			
		}catch(Exception e){
			System.out.println("Failed to read");
		}
		System.out.println("Author succesfully added");
		//construct new author and add to hashmap
		Author newAuthor = new Author(id, name);
		mapA.put(id, newAuthor);


	}
	
	public static void update() {
		updateAuthors();
		Author.menu();
		
	}
	public static void updateAuthors() {
		int id;
		String name;
		// read all authors 
		try(BufferedReader bufStream = new BufferedReader (new FileReader(fileName))){
		String line = bufStream.readLine();
		while(line!=null) {
			id = Integer.parseInt(line.substring(0, line.indexOf(",")));
			name = line.substring(line.indexOf(",")+1, line.length());
			System.out.println("Author Name: "+name+" with ID: "+id);
			line = bufStream.readLine();
		}
		
		}catch(Exception e) {
		System.out.println("Authors are empty");
		}
		//choose id and take input for name
		System.out.println("Choose author ID");
		//make sure option exists
		int optionId = validOption();
		System.out.println("Change Author name");
		name = Main.sc.nextLine();
		
		try{
			//read file and put into buffer
			BufferedReader bufStream = new BufferedReader (new FileReader(fileName));
			StringBuffer buffer = new StringBuffer();
			String line;
			//iterate through buffer
			while ((line = bufStream.readLine()) != null) {
				//find id and isolate
				String optionIdString = Integer.toString(optionId);
			if(optionIdString.equals(line.substring(0, 1))) {
	            line =  optionId + "," + name;
	            buffer.append(line);
	            buffer.append('\n');
			}else {
				//if input doesn't match id read and write rest of file
				buffer.append(line);
	            buffer.append('\n');
			}
	        }
			String inputString = buffer.toString();
			System.out.println(inputString);
			FileWriter filewriter = new FileWriter(new File(fileName));
			filewriter.write(inputString);
			filewriter.close();
			
		}catch(Exception e){
			System.out.println("Unable to edit");
		}
		
		
		System.out.println("Author succesfully updated");
		Author a = mapA.get(optionId);
		a.authorName = name;
	}
	public static void delete() {
		
			deleteAuthorwithBooks();
			Author.menu();
		
	}
	public static void deleteAuthors() {
		//reads file
		try(BufferedReader bufStream = new BufferedReader (new FileReader(fileName))){
			String line = bufStream.readLine();
			while(line!=null) {
				int id = Integer.parseInt(line.substring(0, line.indexOf(",")));
				String name = line.substring(line.indexOf(",")+1, line.length());
				System.out.println("Author Name: "+name+" with ID: "+id);
				line = bufStream.readLine();
			}
			
			}catch(Exception e) {
			System.out.println("Authors are empty");
			}
			String userInput;
			System.out.println("Choose author ID");
			userInput = Main.sc.nextLine();
			
			try{
				//reads file and puts into buffer
				BufferedReader bufStream = new BufferedReader (new FileReader(fileName));
				StringBuffer buffer = new StringBuffer();
				String line;
				while ((line = bufStream.readLine()) != null) {
					//finds id and skips line then appends rest of file
				if(userInput.equals(line.substring(0, 1))) continue; {
		            buffer.append(line);
		            buffer.append('\n');
		        }
				String inputString = buffer.toString();
				FileWriter filewriter = new FileWriter(new File(fileName));
				filewriter.write(inputString);
				filewriter.close();
				bufStream.close();
				}}catch(Exception e){
				System.out.println("Unable to edit");
			}
			
			System.out.println("Author succesfully deleted");
			
	}
	public static void deleteAuthorwithBooks() {
		//reads file
				try(BufferedReader bufStream = new BufferedReader (new FileReader(fileName))){
					String line = bufStream.readLine();
					while(line!=null) {
						int id = Integer.parseInt(line.substring(0, line.indexOf(",")));
						String name = line.substring(line.indexOf(",")+1, line.length());
						System.out.println("Author Name: "+name+" with ID: "+id);
						line = bufStream.readLine();
					}
					
				}catch(Exception e) {
					System.out.println("Authors are empty");
				}
				String userInput;
				System.out.println("Choose author ID");
				userInput = Main.sc.nextLine();
					
				try(BufferedReader buffStream = new BufferedReader (new FileReader(fileName))) {
					StringBuffer buffer = new StringBuffer();
					String line;
					while ((line = buffStream.readLine()) != null) {
						//finds id and skips line then appends rest of file
						if(userInput.equals(line.substring(0, 1))) continue; {
				            buffer.append(line);
				            buffer.append('\n');
				        }
						String inputString = buffer.toString();
						FileWriter filewriter = new FileWriter(new File(fileName));
						filewriter.write(inputString);
						filewriter.close();
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Unable to edit author");
				}
				try (BufferedReader bufffStream = new BufferedReader ( new FileReader("resources/lms/BooksTest.txt"))) {
					StringBuffer buffer = new StringBuffer();
					String line;
					while ((line = bufffStream.readLine()) != null) {
						String [] arr = line.split(",");
						if(userInput.equals(arr[2])) {
							Books.mapB.remove(Integer.parseInt(arr[0]));
							continue;
						} else {
							buffer.append(line);
							buffer.append('\n');
						}
					}
					String inputString = buffer.toString();
					FileWriter filewriter = new FileWriter(new File("resources/lms/BooksTest.txt"));
					filewriter.write(inputString);
					filewriter.close();
				}catch(Exception e){
					System.out.println("Unable to edit book");
				}
				
				System.out.println("Author's Books succesfully deleted");
	}
	
	public static int validOption() {
		//input validation for scanner
		String userInput;
		int number = 0;
		boolean hasValidOption =  false;
		//find highest value in map to add new options
		int goBack = Collections.max(mapA.keySet()) + 1;
		do {
			try {
				userInput = Main.sc.nextLine();
				number = Integer.parseInt(userInput);
				if (mapA.containsKey(number) || number == goBack) {
					hasValidOption = true;
				} else {
					System.out.println("Please enter a valid option");
				}
			}catch(NumberFormatException e) {
				System.out.println("Please enter valid option");
			}
		}while(!hasValidOption);
		return number;	
	}
}
