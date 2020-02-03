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

public class Books {
	// global variables
	int bookID;
	String bookName;
	int authorID;
	int publisherID;
	static int nextID;
	static String fileName = "resources/lms/BooksTest.txt";
	static HashMap<Integer, Books> mapB = new HashMap<>();

	// constructor used in hashmap
	public Books(int bookID, String bookName, int authID, int pubID) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.authorID = authID;
		this.publisherID = pubID;
	}

	public static void menu() {

		System.out.println("\n" + "Select 1 to add Book");
		System.out.println("Select 2 to see all Books");
		System.out.println("Select 3 to update Book");
		System.out.println("Select 4 to delete Book");
		System.out.println("Select 5 to return to previous menu");
		// validate option
		int option = Main.validInt(5);

		switch (option) {
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

		try (BufferedReader bufStream = new BufferedReader(new FileReader(fileName))) {
			String line = bufStream.readLine();
			while (line != null) {
				Integer bookId = Integer.parseInt(line.substring(0, line.indexOf(",")));
				String bookName = line.substring(line.indexOf(",") + 1, line.length());
				System.out.println("Book Name: " + bookName + " with ID: " + bookId);
				line = bufStream.readLine();
			}

		} catch (Exception e) {
			System.out.println("Books are empty");
		}

		Books.menu();

	}

	public static void add() {

		int id = 0;
		String name = null;
		System.out.println("Add new book");
//		name = Main.sc.nextLine();
		name = Main.inputValid();
		// read authors
		Author.readAuthors();

		// used to add new author if not in system
		int createNew;
		if (Author.mapA.isEmpty()) {
			createNew = 0;
		} else {
			// finds max value and adds to it
			createNew = Collections.max(Author.mapA.keySet()) + 1;
		}
		// iterate through authors and choose authID
		System.out.println("Enter " + createNew + " to create a new author");
		System.out.println("Choose author ID");
		int optionIdA = Author.validOption();
		int authorId;
		if (optionIdA == createNew) {
			Author.addAuthors();

			// construct new author and add to hashmap
			Author newAuthor = new Author(id, name);
			Author.mapA.put(id, newAuthor);

			// most recent author created
			authorId = Collections.max(Author.mapA.keySet());
		} else {
			// if not, create new, then id is same as input
			authorId = optionIdA;
		}

		Publisher.readPublishers();

		if (Publisher.mapP.isEmpty()) {
			createNew = 0;
		} else {
			createNew = Collections.max(Publisher.mapP.keySet()) + 1;
		}
		// iterate through authors and choose authID
		System.out.println("Enter " + createNew + " to add new Publisher");
		System.out.println("Choose Publisher ID");
		int optionIdP = Publisher.validOption();
		int publisherId;
		if (optionIdP == createNew) {
			Publisher.addPublishers();
			// most recent author created
			publisherId = Collections.max(Publisher.mapP.keySet());
		} else {
			publisherId = optionIdP;
		}

		// write book
		try (FileWriter filewriter = new FileWriter(new File(fileName), true)) {
			// increment id and add userinput for name
			id = nextID;
//			name = userInput;
			filewriter.write(nextID + "," + name + "," + optionIdA + "," + optionIdP + "\n");
			nextID++;
			authorId = Collections.max(Author.mapA.keySet());
			publisherId = Collections.max(Publisher.mapP.keySet());

		} catch (Exception e) {
			System.out.println("Failed to read");
		}

		System.out.println("Book succesfully added");

		// construct new author and add to hashmap
		Books newBook = new Books(id, name, authorId, publisherId);
		mapB.put(id, newBook);

		Books.menu();

	}

	public static void update() {
		updateBooks();
		Books.menu();

	}

	public static void updateBooks() {

		int id = 0;
		String name;
		// read all authors
		try (BufferedReader bufStream = new BufferedReader(new FileReader(fileName))) {
			String line = bufStream.readLine();
			while (line != null) {
				id = Integer.parseInt(line.substring(0, line.indexOf(",")));
				name = line.substring(line.indexOf(",") + 1, line.length());
				System.out.println("Book Name: " + name + " with ID: " + id);
				line = bufStream.readLine();
			}

		} catch (Exception e) {
			System.out.println("Books are empty");
		}
		// choose id and take input for name
		System.out.println("Choose Book ID or enter 0 to go back");
		int optionId = validOption();
		if(optionId != 0) {
			System.out.println("Change Book name");
	//		name = Main.sc.nextLine();
			name = Main.inputValid();
			// update author
			Author.readAuthors();
	
			int createNew;
			if (Author.mapA.isEmpty()) {
				createNew = 0;
			} else {
				createNew = Collections.max(Author.mapA.keySet()) + 1;
			}
			// iterate through authors and choose authID
			System.out.println("Enter " + createNew + " to create a new author");
			System.out.println("Choose author ID");
			int optionIdA = Author.validOption();
			int authorId;
			if (optionIdA == createNew) {
				Author.addAuthors();
	
				// construct new author and add to hashmap
				Author newAuthor = new Author(id, name);
				Author.mapA.put(id, newAuthor);
	
				// most recent author created
				authorId = Collections.max(Author.mapA.keySet());
			} else {
				authorId = optionIdA;
			}
	
			// upate pub
			Publisher.readPublishers();
	
			if (Publisher.mapP.isEmpty()) {
				createNew = 0;
			} else {
				createNew = Collections.max(Publisher.mapP.keySet()) + 1;
			}
			// iterate through authors and choose authID
			System.out.println("Enter " + createNew + " to add new Publisher");
			System.out.println("Choose Publisher ID");
			int optionIdP = Publisher.validOption();
			int publisherId;
			if (optionIdP == createNew) {
				Publisher.addPublishers();
				// most recent author created
				publisherId = Collections.max(Publisher.mapP.keySet());
			} else {
				publisherId = optionIdP;
			}
			//
			try {
				// read file and put into buffer
				BufferedReader bufStream = new BufferedReader(new FileReader(fileName));
				StringBuffer buffer = new StringBuffer();
				String line;
				// iterate through buffer
				while ((line = bufStream.readLine()) != null) {
					// find id and isolate
					String optionIdString = Integer.toString(optionId);
					if (optionIdString.equals(line.substring(0, 1))) {
						// **add authid and pubid
						line = optionId + "," + name + "," + optionIdA + "," + optionIdP;
						buffer.append(line);
						buffer.append('\n');
					} else {
						// if input doesn't match id read rest of file
						buffer.append(line);
						buffer.append('\n');
					}
				}
				String inputString = buffer.toString();
				System.out.println(inputString);
				FileWriter filewriter = new FileWriter(new File(fileName));
				filewriter.write(inputString);
				filewriter.close();
	
			} catch (Exception e) {
				System.out.println("Unable to edit");
			}
	
			System.out.println("Book succesfully updated");
			Books b = mapB.get(optionId);
			b.bookName = name;
		}
	}

	public static void delete() {
		deleteBooks();
		Books.menu();

	}

	public static void deleteBooks() {
		// read book file//
		try (BufferedReader bufStream = new BufferedReader(new FileReader(fileName))) {
			String line = bufStream.readLine();
			while (line != null) {
				int id = Integer.parseInt(line.substring(0, line.indexOf(",")));
				String name = line.substring(line.indexOf(",") + 1, line.length());
				System.out.println("Book Name: " + name + " with ID: " + id);
				line = bufStream.readLine();
			}

		} catch (Exception e) {
			System.out.println("Authors are empty");
		}
//			String userInput;
		System.out.println("Choose Book ID or enter 0 to go back");
		int optionId = validOption();
		if(optionId != 0) {
			try {
				BufferedReader bufStream = new BufferedReader(new FileReader(fileName));
				StringBuffer buffer = new StringBuffer();
				String line;
				while ((line = bufStream.readLine()) != null) {
					// finds id and skips line then appends rest of file
					String optionIdString = Integer.toString(optionId);
					if (optionIdString.equals(line.substring(0, 1)))
						continue;
					{
	//		            line =  userInput + "," + newAuthor;
						buffer.append(line);
						buffer.append('\n');
	//				}else {
	//					buffer.append(line);
	//		            buffer.append('\n');
	//				}
					}
					String inputString = buffer.toString();
	//				System.out.println(inputString);
					FileWriter filewriter = new FileWriter(new File(fileName));
					filewriter.write(inputString);
					filewriter.close();
	
				}
			} catch (Exception e) {
				System.out.println("Unable to edit");
			}
	
			System.out.println("Book succesfully deleted");
		}
	}

	public static int validOption() {
		String userInput;
		int number = 0;
		int goback = 0;
		boolean hasValidOption = false;
		do {
			try {
				userInput = Main.sc.nextLine();
				number = Integer.parseInt(userInput);
				if (mapB.containsKey(number) || number==goback) {
					hasValidOption = true;
				} else {
					System.out.println("Please enter a valid option");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter valid option");
			}
		} while (!hasValidOption);
		return number;
	}

}
