package part01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author F1shG3ck0
 */
/*
 * Menu Manager
 */

public class QUBImages {
	// String array of menu options
	static final String options[] = { "Add Image", "Search", "Display All", "Exit" };
	// Define a System.outstant QUIT
	static final int QUIT = options.length;

	// String array of search menu options
	static final String searchOptions[] = { "Unique id", "Title", "Description", "Type", "Date range", "Exit" };
	// define a System.outstant SEARCH_QUIT
	static final int SEARCH_QUIT = searchOptions.length;

	// A menu title
	static String title = "QUB Images";
	static String searchTitle = "Search Fields";

	// Define a menu using title & options
	//// main menu
	static Menu QUBImagesMenu = new Menu(title, options);
	//// search menu
	static Menu SearchMenu = new Menu(searchTitle, searchOptions);

	// define an ImageManager Instance
	static ImageManager manager = new ImageManager();
	// Define a Scanner
	static Scanner input = new Scanner(System.in);

	// userSearch - for break out
	static int userSearch;

	// date formatter
	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/**
	 * Main Method to manage the initial menu
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// preload images
		preloadImages();

		int choice;
		do {
			// get user menu option choice
			choice = QUBImagesMenu.getUserChoice();
			// run choice options if quit isn't choice
			if (choice != QUIT) {
				// manage choice
				processChoice(choice);
			}
		} while (choice != QUIT);
		// exit message
		System.out.println("\nGoodbye!");
		// clase scanner
		input.close();
	}

	/**
	 * Method to preload image objects (allows search to be possible)
	 */
	private static void preloadImages() {

		// catch argument (test for any errors easily)
		try {
			ImageRecord img1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.",
					ImageType.ASTRONOMY, "01-01-2023", "Andromeda.png");
			manager.addImage(img1);
			ImageRecord img2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.",
					ImageType.ARCHITECTURE, "01-02-2023", "LanyonQUB.png");
			manager.addImage(img2);
			ImageRecord img3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.",
					ImageType.SPORT, "01-03-2023", "KermitGolf.png");
			manager.addImage(img3);
			ImageRecord img4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.",
					ImageType.LANDSCAPE, "01-04-2023", "Mournes.png");
			manager.addImage(img4);
			ImageRecord img5 = new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.",
					ImageType.PORTRAIT, "01-03-2023", "Homer.png");
			manager.addImage(img5);
			ImageRecord img6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE,
					"01-04-2023", "RedKite.png");
			manager.addImage(img6);
			ImageRecord img7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.",
					ImageType.AERIAL, "01-05-2023", "CentralPark.png");
			manager.addImage(img7);
			ImageRecord img8 = new ImageRecord("Apples", "A bunch of apples.", ImageType.FOOD, "01-06-2023",
					"Apples.png");
			manager.addImage(img8);
			ImageRecord img9 = new ImageRecord("Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER,
					"01-07-2023", "ChatGPT.png");
			manager.addImage(img9);

		} catch (Exception e) {
			// should not get here - preloaded images should be correct
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Method to process user choice - calls methods (unless invalid entry)
	 * 
	 * @param choice
	 */
	private static void processChoice(int choice) {
		// use switch for 4 options
		switch (choice) {
		case (1):
			// add image
			System.out.println("");
			newImage();
			// don't allow for fall through
			break;
		case (2):
			// search menu
			do {
				System.out.println("");
				// get choise from user
				userSearch = SearchMenu.getUserChoice();
				// exit option from search is managed
				if (userSearch != SEARCH_QUIT) {
					// manage search option
					processSearchChoice(userSearch);
				}
				// user search < 0 or user search > SEARCH_QUIT
			} while (!(userSearch >= 0 && userSearch <= SEARCH_QUIT));
			if (userSearch == SEARCH_QUIT) {
				//no break before new menu
				System.out.println("");
			}
			break;
		case (3):
			// display all
			System.out.println("");
			displayAll();
			break;
		default:
			// user enter > 4
			System.err.println("Not a valid choice");
			System.out.println("");
		}
	}

	/**
	 * Method to display all Image records arranged by date.
	 */
	private static void displayAll() {
		System.out.println("Displaying All Images:\n");
		// get ImageAlbum for all images
		ImageAlbum allImages = manager.getAllImages();

		// how many images
		System.out.println(allImages.getCollectionSize() + " Image(s)");

		// iterate through images
		// first image first
		ImageRecord current = allImages.getFirst();
		// check if the next/first exists
		while (current != null) {
			// print current (as it changes every repeat
			System.out.println(current);
			// change current to next image
			current = allImages.getNext();

		}
		// blank line
		System.out.println();
		//break out of search menu (sucessful search)
		userSearch = 0;

	}

	/**
	 * Method to create ImageRecord Image and add to Image Manager collection.
	 */
	private static void newImage() {
		// repeat until title is not null
		String title;
		do {
			System.out.print("Enter Image Record Title: ");
			title = input.nextLine().trim();
		} while (title == null || title.equals(""));
		// repeat until description is not null
		String desc;
		do {
			System.out.print("Enter Image Record Description: ");
			desc = input.nextLine().trim();
		} while (desc == null || desc.equals(""));

		// repeat until valid genre is chosen
		// no valid
		boolean validGenre = false;
		ImageType typeGenre = null; // initialise to stop may not have been initialised warning
		do {
			// print out options for user
			System.out.println(ImageType.ASTRONOMY + ", " + ImageType.ARCHITECTURE + ", " + ImageType.SPORT + ", "
					+ ImageType.LANDSCAPE + ", " + ImageType.PORTRAIT + ", " + ImageType.NATURE + ", "
					+ ImageType.AERIAL + ", " + ImageType.FOOD + ", " + ImageType.OTHER);
			// get user input
			System.out.print("Enter Image Record Genre: ");
			String genre = input.nextLine().trim();
			// user switch to change data type to enum ImageType
			switch (genre.toUpperCase()) {
			case ("ASTRONOMY"):
				typeGenre = ImageType.ASTRONOMY;
				validGenre = true;
				break;
			case ("ARCHITECTURE"):
				typeGenre = ImageType.ARCHITECTURE;
				validGenre = true;
				break;
			case ("SPORT"):
				typeGenre = ImageType.SPORT;
				validGenre = true;
				break;
			case ("LANDSCAPE"):
				typeGenre = ImageType.LANDSCAPE;
				validGenre = true;
				break;
			case ("PORTRAIT"):
				typeGenre = ImageType.PORTRAIT;
				validGenre = true;
				break;
			case ("NATURE"):
				typeGenre = ImageType.NATURE;
				validGenre = true;
				break;
			case ("AERIAL"):
				typeGenre = ImageType.AERIAL;
				validGenre = true;
				break;
			case ("FOOD"):
				typeGenre = ImageType.FOOD;
				validGenre = true;
				break;
			case ("OTHER"):
				typeGenre = ImageType.OTHER;
				validGenre = true;
				break;
			default:
				// if not an enum - or null
				System.err.println("Not a vaild genre please try again");
			}
		} while (!validGenre);

		// verify date format is correct
		String date;
		boolean nums = false;
		do {
			System.out.print("Enter Image Record Date (dd-mm-yyyy): ");
			// get input and trim any excess
			date = input.nextLine().trim();
			try {
				@SuppressWarnings("unused")
				LocalDate verifyDate = LocalDate.parse(date, FORMATTER);
				nums = true;
			} catch (Exception e) {
				System.err.println("Please enter a valid date");
			}
		} while (date == null || !(nums));

		// verify file is .png
		String thumbnail;
		do {
			System.out.print("Enter Image Thumbnail (.png): ");
			// get input and trim any excess
			thumbnail = input.nextLine().trim();
		} while (thumbnail == null || !(thumbnail.endsWith(".png")) || !(thumbnail.length() > 3));

		try {
			System.out.println();
			ImageRecord newImg = new ImageRecord(title, desc, typeGenre, date, thumbnail);
			// add image to sorted collection (by date)
			manager.addImage(newImg);
			System.out.println("Image added Sucessfully");

		} catch (Exception e) {
			// error message
			System.err.println(e.getMessage());
			System.err.println("INVALID please try again");
		}

	}

	/**
	 * Method to process user search choice - calls methods (unless invalid entry)
	 * 
	 * @param choice
	 */
	private static void processSearchChoice(int choice) {
		switch (choice) {
		case (1):
			// unique id
			System.out.println();
			uniqueID();
			break;
		case (2):
			// title
			System.out.println();
			titleSearch();
			break;
		case (3):
			// description
			System.out.println();
			descSearch();
			break;
		case (4):
			// type
			System.out.println();
			typeSearch();
			break;
		case (5):
			// date range
			System.out.println();
			dateRangeSearch();
			break;
		default:
			System.err.println("Invalid search argument");
			System.out.println("");
		}
	}

	/**
	 * Method to display search through date range.
	 */
	private static void dateRangeSearch() {

		// validation
		String startDate, endDate;
		LocalDate sDate, eDate;
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		int count = 0;

		// page heading
		System.out.println("Search Range:");

		// do will always run at least once
		boolean startTrue;
		do {
			if (count != 0) {
				System.out.println("Invalid Start Date. Try again.");
			}
			System.out.print("\tStart Date: (dd-mm-yyyy): ");
			// remove white space
			startDate = input.nextLine().trim();
			// increment count
			count++;
			try {
				sDate = LocalDate.parse(startDate, FORMATTER);
				startTrue = true;
			} catch (Exception e) {
				startTrue = false;
			}
			// System.outditions for repeat (startDate is null or empty)
		} while (startDate == null || !(startTrue));

		// reset count
		count = 0;
		// do will always run at least once
		boolean endTrue;
		do {
			if (count != 0) {
				System.out.print("Invalid End Date try again");
			}
			System.out.print("\tEnd Date: (dd-mm-yyyy): ");
			// remove white space
			endDate = input.nextLine().trim();
			// increment count
			count++;
			try {
				eDate = LocalDate.parse(endDate, FORMATTER);
				// at this point
				endTrue = true;
			} catch (Exception e) {
				endTrue = false;
			}
			// System.outditions for repeat (endDate is null or empty)
		} while (endDate == null || !(endTrue));

		// to remove initalisation errors (the try catches should mean these are valid
		// and return no error)
		sDate = LocalDate.parse(startDate, FORMATTER);
		eDate = LocalDate.parse(endDate, FORMATTER);

		// get collection inside date range
		ImageAlbum dates = manager.searchDates(sDate, eDate);

		// how many images
		System.out.println(dates.getCollectionSize() + " Image(s)");

		// filter through records
		ImageRecord current = dates.getFirst();
		while (current != null) {
			// print out records
			System.out.println(current);
			current = dates.getNext();

		}
		System.out.println();
		userSearch = 0;

	}

	/**
	 * Method to display search by Genre.
	 */
	private static void typeSearch() {
		// validation
		// initialise variables
		boolean valid = false;
		String genre;
		ImageType typeGenre;
		while (!valid) {
			try {
				// print out genre options
				System.out.println(ImageType.ASTRONOMY + ", " + ImageType.ARCHITECTURE + ", " + ImageType.SPORT + ", "
						+ ImageType.LANDSCAPE + ", " + ImageType.PORTRAIT + ", " + ImageType.NATURE + ", "
						+ ImageType.AERIAL + ", " + ImageType.FOOD + ", " + ImageType.OTHER);
				System.out.print("Search Genre: ");
				// remove white space + change to upppercase
				genre = input.nextLine().trim().toUpperCase();
				System.out.println();
				// use switch to find correct genre
				switch (genre) {
				case ("ASTRONOMY"):
					typeGenre = ImageType.ASTRONOMY;
					// print out search heading
					System.out.println("Astronomy: ");
					break;
				case ("ARCHITECTURE"):
					typeGenre = ImageType.ARCHITECTURE;
					System.out.println("Archtecture: ");
					break;
				case ("SPORT"):
					typeGenre = ImageType.SPORT;
					System.out.println("Sport: ");
					break;
				case ("LANDSCAPE"):
					typeGenre = ImageType.LANDSCAPE;
					System.out.println("Landscape: ");
					break;
				case ("PORTRAIT"):
					typeGenre = ImageType.PORTRAIT;
					System.out.println("Portrait: ");
					break;
				case ("NATURE"):
					typeGenre = ImageType.NATURE;
					System.out.println("Nature: ");
					break;
				case ("AERIAL"):
					typeGenre = ImageType.AERIAL;
					System.out.println("Aerial: ");
					break;
				case ("FOOD"):
					typeGenre = ImageType.FOOD;
					System.out.println("Food: ");
					break;
				default:
					System.err.println("No such genre - displaying other");
					// fall through to other
				case ("OTHER"):
					typeGenre = ImageType.OTHER;
					System.out.println("Other: ");
					break;
				}

				// get collection according to genre
				ImageAlbum genres = manager.searchGenre(typeGenre);
				// how many images
				System.out.println(genres.getCollectionSize() + " Image(s)");
				// iterate through ImageAlbum collection
				ImageRecord current = genres.getFirst();
				while (current != null) {
					// print records
					System.out.println(current);
					current = genres.getNext();

				}
				System.out.println();
				// successful genre search
				valid = true;
			} catch (Exception e) {
				System.err.println("Error - genre");
				// reset buffer
				input.nextLine();
			}
		}
		userSearch = 0;

	}

	/**
	 * Method to display search by description.
	 */
	private static void descSearch() {
		// validation
		// initialise variables
		boolean valid = false;
		String description;
		while (!valid) {
			try {
				// prompt user
				System.out.print("Search Description field: ");
				// remove white space
				description = input.nextLine().trim();
				System.out.println();

				// get collection according to part of description
				ImageAlbum descriptions = manager.searchDescription(description);
				// how many images
				System.out.println(descriptions.getCollectionSize() + " Image(s)");
				// iterate through ImageAlbum collection
				ImageRecord current = descriptions.getFirst();
				while (current != null) {
					// print records
					System.out.println(current);
					current = descriptions.getNext();

				}
				System.out.println();
				// successful description search
				valid = true;
			} catch (Exception e) {
				System.err.println("Error - description");
				// reset buffer
				input.nextLine();
			}
		}
		userSearch = 0;
	}

	/**
	 * Method to display search by title.
	 */
	private static void titleSearch() {
		// validation
		// initialise variables
		boolean valid = false;
		String title;
		while (!valid) {
			try {
				// prompt user
				System.out.print("Search Title field: ");
				// remove white space
				title = input.nextLine().trim();
				System.out.println();

				// get collection according to part of title
				ImageAlbum titles = manager.searchTitle(title);
				// how many images
				System.out.println(titles.getCollectionSize() + " Image(s)");
				// iterate through ImageAlbum collection
				ImageRecord current = titles.getFirst();
				while (current != null) {
					// print records
					System.out.println(current);
					current = titles.getNext();

				}
				System.out.println();
				// successful title search
				valid = true;
			} catch (Exception e) {
				System.err.println("Error - title");
				// reset buffer
				input.nextLine();
			}
		}
		userSearch = 0;

	}

	/*
	 * Method to display search for unique id record
	 */
	private static void uniqueID() {
		// validation
		boolean valid = false;
		int uID;
		while (!valid) {
			try {
				// prompt user
				System.out.print("Search Unique Image ID: ");
				// get integer from user
				uID = input.nextInt();
				// check for valid integer
				if (uID > 0) {
					System.out.println();
					if (manager.searchId(uID) != null) {
						System.out.println(manager.searchId(uID));

					} else {
						System.out.println("No Image found");
					}
					System.out.println();
					valid = true;
				} else {
					System.err.println("Please enter a number greater than 0");
				}

			} catch (Exception e) {
				System.err.println("Invalid Input please enter an integer");
				input.nextLine();
			}
		}
		userSearch = 0;

	}

}
