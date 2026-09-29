package part02;

import java.awt.Color;
import java.awt.Font;

import console.Console;

/**
 * @author F1shG3ck0
 */
/**
 * Object Class - Menu instance for console display
 */
public class Menu {
	// instance data
	private String items[];
	private String title;
	private Console con;
	private Color current;

	// red colour for error messages
	static final Color myRed = new Color(222, 65, 33);

	/**
	 * Constructor method - initialise instance data
	 */
	public Menu(String title, String data[], Console con) {
		this.title = title;
		this.items = data;
		// get reference to pre-existing console
		this.con = con;
		// store the main colour of the menu (in order to reset after error)
		this.current = con.getColour();
	}

	/**
	 * Method to display options screen to the user - using console
	 */
	private void display() {
		// change font size to be larger for title
		con.setFont(new Font("Courier", Font.BOLD, 30));
		// print out menu title
		con.println(title);
		// splitter row between title and options
		for (int count = 0; count < title.length(); count++) {
			con.print("+");
		}
		// font size changed backfor options
		con.setFont(new Font("Courier", Font.BOLD, 20));
		// blank line
		con.println();
		// print out options with correlation number
		for (int option = 1; option <= items.length; option++) {
			con.println(option + ". " + items[option - 1]);
		}
		// blank line
		con.println();
	}

	/**
	 * get the users input and return to QUBImages (for processing)
	 * 
	 * @return
	 */
	public int getUserChoice() {
		// ensure valid choice - must be integer
		boolean valid = false;
		int value = 0;
		while (!valid) {
			try {
				// display menu
				display();
				// prompt uset input
				con.print("Enter Selection: ");
				// get input integer
				value = Integer.parseInt(con.readLn());
				// break out of loop after valid input is entered
				con.clear();
				valid = true;
			} catch (Exception e) {
				// if an integer is not enterd an exception occurs
				// user warning and try again
				con.clear();
				con.setColour(myRed); // red error messsage
				con.println("Invalid input please enter an integer");
				// reset colour
				con.setColour(current);
			}

		}
		// return valid value
		return value;

	}
}
