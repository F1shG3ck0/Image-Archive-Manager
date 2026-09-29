package part01;

import java.util.Scanner;
/**
 * @author F1shG3ck0
 */
/**
 * Object Class - Menu instance for console display
 */
public class Menu {
	//instance data
	private String items[];
	private String title;
	private Scanner input;

	/**
	 * Constructor method - initialise instance data
	 */
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	/**
	 * Method to display options screen to the user
	 */
	private void display() {
		//print out menu title
		System.out.println(title);
		//splitter row between title and options
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		//blank line
		System.out.println();
		//print out options with correlation number
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		//blank line
		System.out.println();
	}

	/**
	 * get the users input and return to QUBImages (for processing)
	 * @return
	 */
	public int getUserChoice() {
		//ensure valid choice - must be integer
		boolean valid = false;
		int value = 0;
		while (!valid) {
			try {
				//display menu
				display();
				//prompt uset input
				System.out.print("Enter Selection: ");
				//get input integer
				value = input.nextInt();
				//break out of loop after valid input is entered
				valid = true;
			} catch (Exception e) {
				//if an integer is not enterd an exception occurs
				//user warning and try again
				System.err.println("Invalid input please enter an integer");
				//reset buffer
				input.nextLine();
			}

		}
		//return valid value
		return value;

	}
}
