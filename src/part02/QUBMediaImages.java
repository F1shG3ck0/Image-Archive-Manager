package part02;

//import part 1 classes
import part01.ImageManager;
import part01.ImageRecord;
import part01.ImageAlbum;
import part01.ImageType;

//console
import java.awt.Color;
import java.awt.Font;
import console.Console;
//date
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//images
import javax.swing.ImageIcon;


/**
 * @author F1shG3ck0
 */
/**
 * Menu Manager working with the external console library
 */
public class QUBMediaImages {

	// String array of menu options
	static final String options[] = { "Add Image", "Search", "Display All", "Exit" };
	// Define a constant QUIT
	static final int QUIT = options.length;

	// String array of search menu options
	static final String searchOptions[] = { "Unique id", "Title", "Description", "Type", "Date range", "Exit" };
	// define a constant SEARCH_QUIT
	static final int SEARCH_QUIT = searchOptions.length;

	// A menu title
	static String title = "QUB Images";
	static String searchTitle = "Search Fields";

	// red colour for error messages
	static final Color myRed = new Color(222, 65, 33);
	// text for image creation is green.
	// I made my own green because the colour.GREEN was too vibrant
	static final Color myGreen = new Color(89, 168, 50);
	static Color custom;

	// define a console
	static final Console con = createConsole();

	// Define a menu using title & options
	//// main menu
	static Menu QUBImagesMenu = new Menu(title, options, con);
	//// search menu
	static Menu SearchMenu = new Menu(searchTitle, searchOptions, con);

	// define an ImageManager Instance
	static ImageManager manager = new ImageManager();

	// userSearch - for break out
	static int userSearch;

	// date formatter
	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/**
	 * Main Method to run the set up and loops
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// preload images
		preloadImages();

		int choice;
		// repeat menu until quit entered
		do {
			con.setTitle(title);
			// get user menu option choice
			choice = QUBImagesMenu.getUserChoice();
			// run choice options if quit isn't choice
			if (choice != QUIT) {
				// manage choice
				processChoice(choice);
			}
		} while (choice != QUIT);
		// exit message
		con.setTitle("Exit");
		con.println("Goodbye!");
		con.println("Hit RETURN to Continue");
		// Wait until line input is received
		con.readLn();
		// close console
		con.setVisible(false);
	}

	/**
	 * Creates a Console instance
	 * 
	 * @return
	 */
	private static Console createConsole() {
		// create console
		Console con = new Console(true);
		// set title of console
		con.setTitle(title);
		// set starting size
		con.setSize(500, 400);
		// set background colour for console
		con.setLocationRelativeTo(null);
		// make console visible
		con.setVisible(true);
		// set colour and font
		con.setBgColour(Color.BLACK);
		con.setFont(new Font("Courier", Font.BOLD, 20));
		// set text colour for console
		con.setColour(Color.WHITE);
		return con;
	}

	/**
	 * Method to preload image objects (allows search to be possible)
	 */
	private static void preloadImages() {

		// catch argument (test for any errors easily)
		// try preloading images
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
			// should not get here - preloaded images should be corrects
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
			// set size (wider and taller)
			con.setSize(550, 500);
			// centre
			con.setLocationRelativeTo(null);
			// change console title
			con.setTitle("Add Image");
			// run new image method
			newImage();
			// reset size (return to menu)
			con.setSize(500, 350);
			// centralise
			con.setLocationRelativeTo(null);
			con.setColour(Color.WHITE);
			// don't allow for fall through
			break;
		case (2):
			// search menu
			do {
				// search menu has different colour (diferenciate easier)
				// if an error message has happened ensure colour reset
				con.setColour(Color.pink);
				// change console title to the search console title
				con.setTitle(searchTitle);
				// get choise from user
				userSearch = SearchMenu.getUserChoice();
				// exit option from search is managed
				if (userSearch != SEARCH_QUIT) {
					// manage search option
					processSearchChoice(userSearch);
				}
				// user search < 0 or user search > SEARCH_QUIT
			} while (!(userSearch >= 0 && userSearch <= SEARCH_QUIT));
			// reset color to white for main menu
			con.setColour(Color.WHITE);
			break;
		case (3):
			// display all
			// set title for all images title
			con.setTitle("All Images");
			// run display all method
			displayAll();
			break;
		default:
			// RED ERROR MESSAGES
			con.setColour(myRed);
			// user enter > 4
			// number not valid
			// (non integer input managed within menu class)
			con.println("Not a valid choice");
			// reset colour
			con.setColour(Color.white);
		}
	}

	/**
	 * Method to display all Image records arranged by date.
	 */
	private static void displayAll() {
		// reset console
		con.clear();
		// get ImageAlbum for all images
		ImageAlbum allImages = manager.getAllImages();
		// iterate through images
		String heading = "Displaying All " + allImages.getCollectionSize() + " Image(s):";

		// manage filter through
		// ensure there is images (there should be due to preload)
		if (allImages.getCollectionSize() != 0) {
			navigateAlbum(allImages, heading);
		} else {
			con.println("No images found");
			con.println("Hit RETURN to return to menu");
			con.readLn();

			con.clear();
			// change search choice to get back to QUB Images menu
			userSearch = 0;
		}

	}

	/**
	 * Method to find the Images folder within the files and get the image thumbnail
	 * passed printed to the console resizing of the console to always fit the image
	 * 
	 * @param thumbnail
	 */
	private static void printImage(String thumbnail) {
		// get the user directory
		String userdir = System.getProperty("user.dir");

		// the path is user directory plus the Images folder.
		String path = userdir + "/Images/";
		try {
			// create an Image Icon object
			ImageIcon img = new ImageIcon(path + thumbnail);
			// default width + calculated high (working with text + additional image)
			int width, height;
			width = 500;
			height = 400 + img.getIconHeight();
			// if the picture is really thin the default of 500 stops the text from looking
			// squished
			if (img.getIconWidth() > width) {
				// add margin of 25 px
				width = img.getIconWidth() + 25;
			}
			// set console size
			con.setSize(width, height);
			// centre newly sized console
			con.setLocationRelativeTo(null);

			// print out the image
			con.print(img);
			// add a carriage return after the image.
			// text goes below image
			con.print("\n");

		} catch (Exception e) {
			// error with filename
			con.println("Filename not be found"); // just in case an invalid filename causes an exception - robustness
		}

	}

	/**
	 * Method to create ImageRecord Image and add to Image Manager collection.
	 */
	private static void newImage() {
		// repeat until title is not null
		con.setColour(myGreen);
		// clear the screen
		con.clear();
		// add starting text
		con.println("Create New Image");
		con.println("++++++++++++++++");
		// make break case obvious
		con.setColour(Color.WHITE);
		con.println("Enter Q to quit");
		con.setColour(myGreen);
		String title;
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		int count = 0;

		// do will always run at least once
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Create New Image");
				con.println("++++++++++++++++");
				con.setColour(Color.WHITE);
				con.println("Enter Q to quit");
				con.setColour(myRed); // red error message (clearer)
				con.println("Invalid please try again");
				con.setColour(myGreen);
			}
			con.print("Enter Image Record Title: ");
			title = con.readLn().trim();
			// if q is entered then terminate
			if (title.equalsIgnoreCase("Q")) {
				con.clear();
				// change termination message colour
				con.setColour(myRed);
				// to break out of image creation
				con.println("Add Image Terminated");
				return;
			}
			// increment count
			count++;
			// conditions for repeat (title is null or empty)
		} while (title == null || title.equals(""));

		// repeat until description is not null
		String desc;
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		count = 0;
		// do will always run at least once
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Create New Image");
				con.println("++++++++++++++++");
				con.setColour(Color.WHITE);
				con.println("Enter Q to quit");
				con.setColour(myGreen);
				con.println("Enter Image Record Title: " + title);
				con.setColour(myRed);// error message red
				con.println("Invalid please try again");
				con.setColour(myGreen);
			}
			con.print("Enter Image Record Description: ");
			desc = con.readLn().trim();
			// if q is entered then terminate
			if (desc.equalsIgnoreCase("Q")) {
				con.clear();
				// change termination message colour
				con.setColour(myRed);
				// to break out of image creation
				con.println("Add Image Terminated");
				return;
			}
			// increment count
			count++;
			// conditions for repeat (description is null or empty)
		} while (desc == null || desc.equals(""));

		// repeat until valid genre is chosen
		// no valid
		boolean validGenre = false;
		ImageType typeGenre = null; // initialise to stop may not have been initialised warning
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		count = 0;
		// do will always run at least once
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Create New Image");
				con.println("++++++++++++++++");
				// quit case obvious
				con.setColour(Color.WHITE);
				con.println("Enter Q to quit");
				con.setColour(myGreen);
				con.println("Enter Image Record Title: " + title);
				con.println("Enter Image Record Description: " + desc);
				con.setColour(myRed);// red error message
				con.println("Not a vaild genre please try again");
				con.setColour(myGreen);// reset colour

			}
			// print out options for user in white to stand out
			con.setColour(Color.white);
			con.println(ImageType.ASTRONOMY + ", " + ImageType.ARCHITECTURE + ", " + ImageType.SPORT + ", "
					+ ImageType.LANDSCAPE + ", " + ImageType.PORTRAIT + ", " + ImageType.NATURE + ", "
					+ ImageType.AERIAL + ", " + ImageType.FOOD + ", " + ImageType.OTHER);
			con.setColour(myGreen); // reset to green
			// get user input
			con.print("Enter Image Record Genre: ");
			String genre = con.readLn().trim();
			// increment count
			count++;
			// user switch to change data type to enum ImageType
			switch (genre.toUpperCase()) {
			// validGenre - break out of loop
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
			// termination case
			case ("Q"):
				con.clear();
				// change colour to make termination more clear
				con.setColour(myRed);
				// to break out of image creation
				con.println("Add Image Terminated");
				// return to break out of addImage method
				return;
			default:
				// if not an enum - or null
				// repeat
			}
			// repeat until a valid genre is entered
		} while (!validGenre);

		// verify date format is correct
		String date;
		// repeat until valid date is given
		// no valid
		boolean nums = false;
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		count = 0;
		// do will always run at least once
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Create New Image");
				con.println("++++++++++++++++");
				con.setColour(Color.WHITE);
				con.println("Enter Q to quit");
				con.setColour(myGreen);
				con.println("Enter Image Record Title: " + title);
				con.println("Enter Image Record Description: " + desc);
				con.println("Enter Image Record Genre: " + typeGenre);
				con.setColour(myRed); // red error message
				con.println("Not a vaild date please try again");
				con.setColour(myGreen);
			}
			con.print("Enter Image Record Date (dd-mm-yyyy): ");
			// get input and trim any excess
			date = con.readLn().trim();
			// quit case
			if (date.equalsIgnoreCase("Q")) {
				// reset console
				con.clear();
				// change colour to make termination more clear
				con.setColour(myRed);
				// to break out of image creation
				con.println("Add Image Terminated");
				return;
			}
			// increment count
			count++;
			// attempt to parse to LocalDate (check if it is a valid date)
			try {
				// warning will be flagged for unused variable as the parsed variable is not
				// used
				@SuppressWarnings("unused")
				// test if the date is actually a valid date (a string should be used to create
				// an image record)
				LocalDate verifyDate = LocalDate.parse(date, FORMATTER);
				// if this point is reached it is valid (no exception thrown)
				nums = true;
			} catch (Exception e) {
			}
			// repeat if date is null or not nums (invalid date)
		} while (date == null || !(nums));

		// verify file is .png
		String thumbnail;
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		count = 0;
		// do will always run at least once
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Create New Image");
				con.println("++++++++++++++++");
				con.setColour(Color.WHITE);
				con.println("Enter Q to quit");
				con.setColour(myGreen);
				con.println("Enter Image Record Title: " + title);
				con.println("Enter Image Record Description: " + desc);
				con.println("Enter Image Record Genre: " + typeGenre);
				con.println("Enter Image Record Date (dd-mm-yyyy): " + date);
				con.setColour(myRed); // red error message
				con.println("Not a vaild thumbnail please try again");
				con.setColour(myGreen);
			}
			// prompt
			con.print("Enter Image Thumbnail (.png): ");
			// get input and trim any excess
			thumbnail = con.readLn().trim();
			// termination case
			if (thumbnail.equalsIgnoreCase("Q")) {
				con.clear();
				// change colour to make termination more clear
				con.setColour(myRed);
				// to break out of image creation
				con.println("Add Image Terminated");
				return;
			}
			// increment count
			count++;
			// repeat if thumbnail is null or doesn't end in .png or the length is is
			// greater than 4 (file name at least one letter besides .png)
		} while (thumbnail == null || !(thumbnail.endsWith(".png")) || !(thumbnail.length() > 4));

		// attempt image creation (previous checks should mean this has to work)
		try {
			con.println();
			// attempt ImageRecord image creation
			ImageRecord newImg = new ImageRecord(title, desc, typeGenre, date, thumbnail);
			// add image to sorted collection (by date)
			manager.addImage(newImg);

		} catch (Exception e) {
			// error message - should not be able to get here but just in case
			con.clear();
			// change to red for error if actually shown
			con.setColour(myRed);
			con.println(e.getMessage());
			con.println("ERROR please try again");
		}

		// reset console
		con.clear();
		// line added in green to top of menu screen
		con.println("Image added Sucessfully");
	}

	/**
	 * Method to process user search choice - calls methods (unless invalid entry)
	 * 
	 * @param choice
	 */
	private static void processSearchChoice(int choice) {
		switch (choice) {
		case (1):
			// unique id - change console name
			con.setTitle("Search by ID");
			uniqueID();
			break;
		case (2):
			// title - change console name
			con.setTitle("Search by Title");
			titleSearch();
			break;
		case (3):
			// description - change console name
			con.setTitle("Search by Description");
			descSearch();
			break;
		case (4):
			// type - change console name
			con.setTitle("Search by Genre");
			typeSearch();
			break;
		case (5):
			// date range - change console name
			con.setTitle("Search by Date Range");
			dateRangeSearch();
			break;
		default:
			// any number not between 1 and 6
			con.setColour(myRed);
			con.println("Invalid search argument");
			// reset at search menu call

		}
	}

	/**
	 * Method to display search through date range.
	 */
	private static void dateRangeSearch() {
		// green
		con.setColour(myGreen);
		// validation
		String startDate, endDate;
		LocalDate sDate, eDate;
		// add count to manage first time run
		// if there is an error the screen will need to be reset
		int count = 0;

		// page heading
		con.println("Search Range:");

		// do will always run at least once
		boolean startTrue;
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Search Range:");
				con.setColour(myRed); // red error message (clearer)
				con.println("Invalid Date try again");
				con.setColour(myGreen);
			}
			con.print("\tStart Date: (dd-mm-yyyy): ");
			// remove white space
			startDate = con.readLn().trim();
			// increment count
			count++;
			try {
				sDate = LocalDate.parse(startDate, FORMATTER);
				startTrue = true;
			} catch (Exception e) {
				startTrue = false;
			}
			// conditions for repeat (startDate is null or empty)
		} while (startDate == null || !(startTrue));

		// reset count
		count = 0;
		// do will always run at least once
		boolean endTrue;
		do {
			if (count != 0) {
				// reset screen after an error
				con.clear();
				con.println("Search Range:");
				con.println("\tStart Date: (dd-mm-yyyy): " + startDate);
				con.setColour(myRed); // red error message (clearer)
				con.println("Invalid Date try again");
				con.setColour(myGreen);
			}
			con.print("\tEnd Date: (dd-mm-yyyy): ");
			// remove white space
			endDate = con.readLn().trim();
			// increment count
			count++;
			try {
				eDate = LocalDate.parse(endDate, FORMATTER);
				// at this point
				endTrue = true;
			} catch (Exception e) {
				endTrue = false;
			}
			// conditions for repeat (endDate is null or empty)
		} while (endDate == null || !(endTrue));

		// to remove initalisation errors (the try catches should mean these are valid
		// and return no error)
		sDate = LocalDate.parse(startDate, FORMATTER);
		eDate = LocalDate.parse(endDate, FORMATTER);

		// get collection inside date range
		ImageAlbum dates = manager.searchDates(sDate, eDate);

		// heading
		String heading = dates.getCollectionSize() + " Image(s) between " + sDate.format(FORMATTER) + " and "
				+ eDate.format(FORMATTER) + ":";

		// manage filter through
		if (dates.getCollectionSize() != 0) {
			// run Album view
			con.setTitle("Date Search: " + startDate + " - " + endDate);
			navigateAlbum(dates, heading);
		} else {
			con.println("No matches found");
			con.println("Hit RETURN to return to menu");
			con.readLn();

			con.clear();
			// reset to main menu colours
			setColour();
			userSearch = 0;
		}

	}

	/**
	 * Method to display search by Genre.
	 */
	private static void typeSearch() {
		// set screen text colour green
		con.setColour(myGreen);
		// validation
		// initialise variables
		String heading = "";
		String genre;
		ImageType typeGenre;
		try {
			// print out genre options - white
			con.setColour(Color.white);
			con.println(ImageType.ASTRONOMY + ", " + ImageType.ARCHITECTURE + ", " + ImageType.SPORT + ", "
					+ ImageType.LANDSCAPE + ", " + ImageType.PORTRAIT + ", " + ImageType.NATURE + ", "
					+ ImageType.AERIAL + ", " + ImageType.FOOD + ", " + ImageType.OTHER);
			con.setColour(myGreen); // reset to green
			con.print("Search Genre: ");
			// remove white space + change to upppercase
			genre = con.readLn().trim().toUpperCase();
			con.println();
			// use switch to find correct genre
			switch (genre) {
			case ("ASTRONOMY"):
				typeGenre = ImageType.ASTRONOMY;
				break;
			case ("ARCHITECTURE"):
				typeGenre = ImageType.ARCHITECTURE;
				break;
			case ("SPORT"):
				typeGenre = ImageType.SPORT;
				break;
			case ("LANDSCAPE"):
				typeGenre = ImageType.LANDSCAPE;
				break;
			case ("PORTRAIT"):
				typeGenre = ImageType.PORTRAIT;
				break;
			case ("NATURE"):
				typeGenre = ImageType.NATURE;
				break;
			case ("AERIAL"):
				typeGenre = ImageType.AERIAL;
				break;
			case ("FOOD"):
				typeGenre = ImageType.FOOD;
				break;
			default:
				heading = ("No such genre '" + genre + "' - displaying 'Other':\n");
				// fall through to other
			case ("OTHER"):
				typeGenre = ImageType.OTHER;
				break;
			}

			// get collection according to genre
			ImageAlbum genres = manager.searchGenre(typeGenre);

			heading += genres.getCollectionSize() + " Image(s) of Genre " + typeGenre + ":";

			// manage filter through
			if (genres.getCollectionSize() != 0) {
				navigateAlbum(genres, heading);
			} else {
				con.println("No matches found");
				con.println("Hit RETURN to return to menu");
				con.readLn();

				con.clear();
				// reset to main menu colours
				setColour();
				userSearch = 0;
			}

		} catch (Exception e) {
			// should not get here (catch just in case)
			con.clear();
			con.println("Error - genre");
		}
	}

	/**
	 * Method to display search by description.
	 */
	private static void descSearch() {
		// console text colour green
		con.setColour(myGreen);
		// validation
		// initialise variables
		String description;

		try {
			// prompt user
			con.print("Search Description field: ");
			// remove white space
			description = con.readLn().trim();
			con.println();

			// get collection according to part of description
			ImageAlbum descriptions = manager.searchDescription(description);

			// console heading
			String heading = descriptions.getCollectionSize() + " Image(s) with Descriptions containing '" + description
					+ "':";

			// manage filter through
			if (descriptions.getCollectionSize() != 0) {
				navigateAlbum(descriptions, heading);
			} else {
				con.println("No matches found");
				con.println("Hit RETURN to return to menu");
				con.readLn();

				con.clear();
				// reset to main menu colours
				setColour();
				userSearch = 0;
			}

		} catch (Exception e) {
			// should not get here
			con.clear();
			con.println("Error - description");

		}
	}

	/**
	 * Method to display search by title.
	 */
	private static void titleSearch() {
		// console text colour green
		con.setColour(myGreen);
		// validation
		// initialise variable
		String title;
		try {
			// prompt user
			con.print("Search Title field: ");
			// remove white space
			title = con.readLn().trim();
			con.println();

			// get collection according to part of title
			ImageAlbum titles = manager.searchTitle(title);
			// create heading string
			String heading = titles.getCollectionSize() + " Image(s) with Titles containing '" + title + "':";

			// manage filter through
			if (titles.getCollectionSize() != 0) {
				navigateAlbum(titles, heading);
			} else {
				// no images in collection
				con.println("No matches found");
				con.println("Hit RETURN to return to menu");
				con.readLn();

				con.clear();
				// reset to main menu colours
				setColour();
				userSearch = 0;
			}

		} catch (Exception e) {
			// should not get here
			con.println("Error - Title");
		}

	}

	/*
	 * Method to display search for unique id record
	 */
	private static void uniqueID() {
		// console text colour green
		con.setColour(myGreen);
		// validation
		boolean valid = false;
		int uID;
		while (!valid) {
			try {
				// prompt user
				con.print("Search Unique Image ID: ");
				// get integer from user
				uID = Integer.parseInt(con.readLn());
				// check for valid integer
				con.clear();
				if (uID > 0) {

					ImageRecord current = manager.searchId(uID);
					setColour(current);
					con.println("Displaying image " + uID);
					con.println();

					if (current != null) {
						con.setTitle(current.getTitle());
						printImage(current.getThumbnail());
						con.println("Title: " + current.getTitle());
						con.println("Description: " + current.getDescription());
						con.println("Genre: " + current.getGenre());
						con.println("Date Taken: " + current.getDate().format(FORMATTER));
						con.println();
						valid = true;
					} else {
						con.clear();
						con.println("Image not found");
						con.println("Hit RETURN to Continue");
						con.readLn();
						con.clear();
						return;
					}
				} else {
					con.clear();
					// red error message
					con.setColour(myRed);
					con.println("Please enter a number greater than 0");
					// reset
					con.setColour(myGreen);
				}

			} catch (Exception e) {
				con.clear();
				// red error message
				con.setColour(myRed);
				con.println("Invalid Input please enter an integer");
				// reset
				con.setColour(myGreen);
			}
			con.println("Hit RETURN to Continue");
			con.readLn();
			con.clear();
			// reset size to standard and centre
			con.setSize(500, 400);
			con.setLocationRelativeTo(null);
			// reset colours for main menu
			setColour();
			userSearch = 0;
		}

	}

	/**
	 * Method to move through the ImageAlbum to view the details of each ImageRecord
	 * 
	 * @param album
	 * @param heading
	 */
	private static void navigateAlbum(ImageAlbum album, String heading) {
		// get the first image in the album
		ImageRecord current = album.getFirst();
		// there is already a check to ensure that there is items in the collection.
		// set colour according to something in the ImageRecord
		setColour(current);
		// initalise count to 0
		int count = 0;
		// initialise move to something to stop may not have been initalised alerts and
		// to stop move from incidently being blank
		String move = "Hi";
		// initialise message to blank (holds the state of the collection later)
		String msg = "";
		// while current isn't null - current is null when exiting collection
		while (current != null) {
			// inside a catch just incase an unseen error occurs (like trying to display a
			// non existant ImageRecord (should not be possible)
			try {
				// change current to next image
				// set colour according to genre
				setColour(current);

				do {
					con.clear();
					con.println(heading);
					con.println("Image " + (count + 1) + " of " + album.getCollectionSize());
					// print current (as it changes every repeat

					// print the image and then the Image details
					printImage(current.getThumbnail());
					con.println("Title: " + current.getTitle());
					con.println("Description: " + current.getDescription());
					con.println("Genre: " + current.getGenre());
					con.println("Date Taken: " + current.getDate().format(FORMATTER));
					con.println();
					// if state is different - eg last image and attempted to go forwards or first
					// images and attempted to go backwards
					//error colour change
					custom = con.getColour();
					con.setColour(myRed);
					con.print(msg);
					//ensure colour sticks (had a bit of a green issue)
					con.setColour(custom);
					
					con.println("Hit RETURN to return to menu");
					//ensure the collection has more than 1 item. 
					if (album.getCollectionSize() != 1) {
						//if current is the last image - no option to go to next (in prompt)
						if (count == album.getCollectionSize() - 1) {
							con.print("(P)revious: ");
						} else if (count == 0) {
							//if current is the first - no option to go to previous (in prompt)
							con.print("(N)ext: ");
						} else {
							//all images in the middle have all options
							con.print("(P)revious or (N)ext: ");
						}
					}
					con.setColour(custom);
					//remove blank space
					move = con.readLn().trim();
					//message is blank (reset if not first image
					msg = "";
					//loop repeats if no P, N or return - (including numbers and others)
				} while (!(move.isBlank()) && !(move.equalsIgnoreCase("P")) && !(move.equalsIgnoreCase("N")));
				//NEXT
				if (move.equalsIgnoreCase("N")) {
					if (count == album.getCollectionSize() - 1) {
						//end of album message
						msg = "Invalid - end of album\n";
					} else {
						current = album.getNext();
						//increment count (manages image _ of _)
						count++;
					}
				//PREVIOUS
				} else if (move.equalsIgnoreCase("P")) {
					if (count == 0) {
						//start of album message (no previous)
						msg = "Invalid - no previous images\n";
					} else {
						current = album.getPrevious();
						//decrement count (manages image _ of _)
						count--;
					}
				//break out of loop if return
				} else if (move.isBlank()) {
					break;
				}

			} catch (Exception e) {
				//if an error occurs - this should not happen
				con.clear();
				con.println("Error");
			}

		}
		//clear console
		con.clear();
		//reset to menu defaults and centeralise
		con.setSize(500, 400);
		con.setLocationRelativeTo(null);
		//reset to menu colours
		setColour();
		userSearch = 0;

	}

	/**
	 * Method to set the background and text colour of each Image according to its genre (allows for different images to be sorted by looks)
	 * Genre works best due to the limited number available with the enum
	 * @param current
	 */
	private static void setColour(ImageRecord current) {
		//initialise (this will change) - stops may not have been initialised error	
		Color picBgColour = new Color(167, 219, 196);
		Color picTxtColour = new Color(37, 46, 42);
		switch (current.getGenre().toString().toUpperCase()) {
		case ("ASTRONOMY"):
			// navy
			picBgColour = new Color(14, 40, 65);
			picTxtColour = new Color(166, 202, 236);
			break;
		case ("ARCHITECTURE"):
			// ivory
			picBgColour = new Color(251, 227, 214);
			picTxtColour = new Color(192, 79, 21);
			break;
		case ("SPORT"):
			// orange
			picBgColour = new Color(250, 182, 164);
			picTxtColour = new Color(128, 53, 14);
			break;
		case ("LANDSCAPE"):
			// green
			picBgColour = new Color(99, 181, 111);
			picTxtColour = new Color(13, 53, 18);
			break;
		case ("PORTRAIT"):
			// yellow
			picBgColour = new Color(255, 226, 133);
			picTxtColour = new Color(192, 79, 21);
			break;
		case ("NATURE"):
			// brown
			picBgColour = new Color(104, 67, 48);
			picTxtColour = new Color(246, 198, 173);
			break;
		case ("AERIAL"):
			// light blue
			picBgColour = new Color(166, 202, 236);
			picTxtColour = new Color(22, 62, 100);
			break;
		case ("FOOD"):
			// pink
			picBgColour = new Color(229, 158, 221);
			picTxtColour = new Color(80, 22, 74);
			break;
		case ("OTHER"):
			// grey
			picBgColour = new Color(174, 174, 174);
			picTxtColour = Color.BLACK;
			break;
		}
		//set with the colour variables (changed depending on genre)
		con.setBgColour(picBgColour);
		con.setColour(picTxtColour);
	}

	/**
	 * Method to reset the screen colours to black and white (for main menu)
	 */
	private static void setColour() {
		con.setBgColour(Color.BLACK);
		con.setColour(Color.WHITE);
	}
}
