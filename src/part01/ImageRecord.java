package part01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author F1shG3ck0
 */
/*
 * Object Class - ImageRecord objects
 */

public class ImageRecord {
	// private instance data
	private int id;
	private String title;
	private String description;
	private ImageType genre;
	private LocalDate dateTaken;
	private String thumbnail;
	// initial id (self incrementing - static)
	private static int currentID = 1;
	// formatter for LocalDate dd-mm-yyyy
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/**
	 * Constructor Method for ImageRecord Object class - throws an exception to
	 * resolve input errors
	 * 
	 * @param title
	 * @param description
	 * @param genre
	 * @param dateTaken
	 * @param thumbnail
	 * @throws Exception
	 */
	public ImageRecord(String title, String description, ImageType genre, String dateTaken, String thumbnail)
			throws Exception {
		// Set id of instance to currentID then increment currentID.
		this.id = currentID++;
		// error string - initialised to be added to if error occurs (checked at end of
		// method)
		String errors = "";
		// attempt to set title (add to error if method returns false)
		if (!setTitle(title)) {
			errors += "Title: Cannot be null\n";
		}
		// attempt to set Description (add to error if method returns false)
		if (!setDescription(description)) {
			errors += "Description: Cannot be null\n";
		}
		// attempt to set Genre (add to error if method returns false)
		if (!setGenre(genre)) {
			errors += "Genre: Cannot be null\n";
		}
		// attempt to set date taken (add to error if method returns false)
		if (!setDate(dateTaken)) {
			errors += "Date: Cannot be null (dd-mm-yyyy)\n";
		}
		// attempt to set thumbnail (add to error if method returns false)
		if (!setThumbnail(thumbnail)) {
			errors += "Thumbnail: Cannot be null must end in .png\n";
		}

		// check for a change in the error variable (if its longer an error has occured)
		if (errors.length() > 0) {
			// throw exception
			throw new Exception(errors);
		}

	}

	// Mutators (setters) for all appropriate instance data values

	/**
	 * Title setter method returns boolean at success/failure adding user title
	 * string to instance title
	 * 
	 * @param title
	 * @return
	 */
	public boolean setTitle(String title) {
		// validation - not null
		if (title != null) {
			this.title = title;
			return true;
		}
		return false;
	}

	/**
	 * Description setter method returns boolean at success/failure adding user
	 * description string to instance description
	 * 
	 * @param description
	 * @return
	 */
	public boolean setDescription(String description) {
		// validation - not null
		if (description != null) {
			this.description = description;
			return true;
		}
		return false;
	}

	/**
	 * Genre setter method returns boolean at success/failure adding enum ImageType
	 * to instance genre
	 * 
	 * @param genre
	 * @return
	 */
	public boolean setGenre(ImageType genre) {
		// validation - not null
		if (genre != null) {
			this.genre = genre;
			return true;
		}
		return false;
	}

	/**
	 * Setter method to set date taken returns boolean at success/failure adding LocalDate date
	 * @param Date
	 * @return
	 */
	public boolean setDate(String Date) {
		try {		
			//validate format - not null (parse to LocalDate to validate)
			this.dateTaken = LocalDate.parse(Date, FORMATTER);	//if flags exception
			//date has been implemented correctly - no exception thrown
			return true;
			
		} catch (Exception e) {
			//return false if Date cannot be parsed to LocalDate
			return false;		
		}
	}

	/**
	 * Setter method to set thumbnail returns boolean at success/failure adding thumbnail
	 * @param thumbnail
	 * @return
	 */
	public boolean setThumbnail(String thumbnail) {
		//validate - not null, .png filetype, filename along with .png
		if (thumbnail != null && thumbnail.endsWith(".png")&& thumbnail.length() >3) {
			this.thumbnail = thumbnail;
			//thumbnail has been implemented correctly
			return true;
		}
		//failure
		return false;
	}

	// Accessors (getters) for all instance data values
	
	/**
	 * Getter Method to return instance id - int
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * Getter Method to return instance title - String
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Getter Method to return instance description - String
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter Method to return instance genre - ImageType
	 * @return
	 */
	public ImageType getGenre() {
		return genre;
	}

	/**
	 * Getter Method to return instance date taken - LocalDate
	 * @return
	 */
	public LocalDate getDate() {
		return dateTaken;
	}

	/**
	 * Getter Method to return instance thumbnail - String
	 * @return
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	
	/**
	 * toString Method returns String for printing instance
	 * @return
	 */
	public String toString() {
		// toString
		// A toString method which return a String (no line breaks) with details of all
		// instance values, appropriately formatted.
		String data = "ID: " + getID();
		data += "\tTitle: " + getTitle();
		data += "\tDescription: " + getDescription();
		data += "\tGenre: " + getGenre().toString();
		data += "\tDate Taken: " + getDate().format(FORMATTER);
		data += "\tThumbnail: " + getThumbnail();
		return data;
	}

}
