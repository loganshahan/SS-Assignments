/**
 * 
 */
package crud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Publisher {

	int publisherID;
	String publisherName;
	static int nextID;
	static String fileName = "resources/lms/PublishersTest.txt";
	static HashMap<Integer, Publisher> mapP = new HashMap<Integer, Publisher>();
	
	public Publisher(int publisherID, String publisherName) {
		this.publisherID = publisherID;
		this.publisherName = publisherName;
	}
	
	public static void menu() {

		
		System.out.println("\n" + "Select 1 to add Publisher");
		System.out.println("Select 2 to see all Publishers");
		System.out.println("Select 3 to update Publisher");
		System.out.println("Select 4 to delete Publisher");
		System.out.println("Select 5 to return to previous menu");

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
		readPublishers();
		Publisher.menu();
	}
	public static void readPublishers() {
		try(BufferedReader bufStream = new BufferedReader (new FileReader(fileName))){
			String line = bufStream.readLine();
			while(line!=null) {
				Integer id = Integer.parseInt(line.substring(0, line.indexOf(",")));
				String name = line.substring(line.indexOf(",")+1, line.length());
//				String address = line.substring(line.indexOf(",")+2, line.length());
//				String address = line.substring(line.indexOf(",") +3,)
				System.out.println("Publisher Name & Address: "+name+  " with ID: "+id);
				line = bufStream.readLine();
			}
			
		}catch(Exception e) {
			System.out.println("Publishers are empty");
		}

	}
	public static void add() {
//		
		addPublishers();
		Publisher.menu();

	}
	public static void addPublishers() {
//		String userInput;
		int id = 0;
		String name = null;
		String address = null;
		System.out.println("Add new publisher name");
//		name = Main.sc.nextLine();
		name = Main.inputValid();
		System.out.println("Add new publisher address");
//		address = Main.sc.nextLine();
		address = Main.inputValid();
		try(FileWriter filewriter = new FileWriter(new File(fileName),true)){
			id= nextID;
//			name = userInput;
			filewriter.write(nextID + "," +name + "," + address + "\n");
			nextID++;
			
			
		}catch(Exception e){
			System.out.println("Failed to read");
		}
		
		
		System.out.println("Author succesfully added");
		Publisher newPublisher = new Publisher(id, name);
		mapP.put(id, newPublisher);
	}
	
	public static void update() {
		
			updatePublishers();
			Publisher.menu();
	}
	public static void updatePublishers() {
		int id;
		String name;
		String address = null;
		try(BufferedReader bufStream = new BufferedReader (new FileReader(fileName))){
			String line = bufStream.readLine();
			while(line!=null) {
				int pubID = Integer.parseInt(line.substring(0, line.indexOf(",")));
				String pubName = line.substring(line.indexOf(",")+1, line.length());
				System.out.println("Author Name: "+pubName+" with ID: "+pubID);
				line = bufStream.readLine();
			}
			
			}catch(Exception e) {
			System.out.println("Publisherss are empty");
			}
				System.out.println("Choose Publisher ID or enter 0 to go back");
				int optionId = validOption();
				if(optionId !=0) {
				System.out.println("Change Pubisher name");
	//			name = Main.sc.nextLine();
				name = Main.inputValid();
				System.out.println("Change publisher address");
	//			address = Main.sc.nextLine();
				address = Main.inputValid();
				try{
					BufferedReader bufStream = new BufferedReader (new FileReader(fileName));
					StringBuffer buffer = new StringBuffer();
					String line;
					while ((line = bufStream.readLine()) != null) {
						String optionIdString = Integer.toString(optionId);
						if(optionIdString.equals(line.substring(0, 1))) {
				            line =  optionId + "," + name + "," + address;
						}
				        buffer.append(line);
				        buffer.append('\n');
					
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
				Publisher p = mapP.get(optionId);
				p.publisherName = name;
				}	
	}
	public static void delete() {
		
			deletePublishers();
			Publisher.menu();
		
	}
	public static void deletePublishers() {
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
		int optionId;
		System.out.println("Choose Publisher ID or enter 0 to go back");
		optionId = validOption();
		userInput =Integer.toString(optionId);
		
		if (optionId != 0) {	
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
					if(userInput.equals(arr[3])) {
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
			
			System.out.println("Publisher's Books succesfully deleted");
		}
	}

	public static int validOption() {
		String userInput;
		int number = 0;
		boolean hasValidOption =  false;
		int goBack;
		if (mapP.isEmpty()) {
			goBack = 0;
		} else {
			goBack = Collections.max(mapP.keySet()) + 1;
		}

		do {
			try {
				userInput = Main.sc.nextLine();
				number = Integer.parseInt(userInput);
				if (mapP.containsKey(number) || number == goBack) {
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
