package part01;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author F1shG3ck0
 */
/*
 * Object class - manager instance to work with data
 */

public class ImageManager {
	// private instance data
	// create an array list to store all image for the instance of ImageManager
	private ArrayList<ImageRecord> collection = new ArrayList<ImageRecord>();

	/**
	 * Method to add an image to the collection of images. (manipulates ImageRecord
	 * objects)
	 * 
	 * @param image
	 */
	public void addImage(ImageRecord image) {
		// – adds a reference to an ImageRecord object to the collection.
		// ensure correct placement
		// sort incoming data as it arrives by date

		// check current length of collection array list
		int size = collection.size();
		// if it is the first object just add it to collection. (no sorting required).
		if (size == 0) {
			collection.add(image);
			return;
		}
		// find insert position
		int index;
		// iterate through array list making comparisons
		for (index = 0; index < size; index++) {
			// compare the dates
			if (collection.get(index).getDate().isAfter(image.getDate())) {
				// break out of for if it is after telling us the index at which to insert the
				// new object
				break;
			}
		}
		// position is the index which the break out of the for occurred
		// mark position

		// add object at position
		collection.add(index, image); // insert value

	}

	/**
	 * Method to search array linearly for specific searchID - returns an ImageRecord
	 * 
	 * @param id
	 * @return
	 */
	public ImageRecord searchId(int id) {
		// returns a reference to an ImageRecord object (identified by id) or null.
		// collection is not sorted by id linear search must be used

		for (int count = 0; count < collection.size(); count++) {
			// compare id of Image record in collections at current index to searched id
			if (collection.get(count).getID() == id) {
				// return image record
				return collection.get(count);
			}
		}
		// no matching image so return null
		return null;
	}

	/**
	 * Method to get RecordAlbum instance made up of an array list of Image Records
	 * with title search matches.
	 * 
	 * @param str
	 * @return
	 */
	public ImageAlbum searchTitle(String str) {
		// returns a reference to an ImageAlbum instance for images
		// with title containing the String str.
		str = str.toLowerCase();// lower case str for case insensitivity
		// new array list to send to new instance of AlbumRecord
		ArrayList<ImageRecord> titles = new ArrayList<ImageRecord>();
		// iterate through the collection - linear search - better for multiple data
		// pieces
		for (int index = 0; index < collection.size(); index++) {
			// check for non case sensitive title fields with full match in part of the
			// field with user input string
			if (collection.get(index).getTitle().toLowerCase().contains(str)) {
				// add match to new titles array list (linear will keep the data date ordered)
				titles.add(collection.get(index));
			}
		}
		// new ImageAlbum instance which needs to be returned
		return new ImageAlbum(titles);
	}

	/**
	 * Method to get RecordAlbum instance made up of an array list of Image Records
	 * with description search matches.
	 * 
	 * @param str
	 * @return
	 */
	public ImageAlbum searchDescription(String str) {
		// returns a reference to an ImageAlbum instance
		// for images with description containing the String str.
		str = str.toLowerCase();// lower case str for case insensitivity
		// new array list to send to new instance of AlbumRecord
		ArrayList<ImageRecord> descriptions = new ArrayList<ImageRecord>();
		// iterate through the collection - linear search - better for multiple data
		// pieces
		for (int index = 0; collection.size() > index; index++) {
			// check for non case sensitive description fields with full match in part of
			// the
			// field with user input string
			if (collection.get(index).getDescription().toLowerCase().contains(str)) {
				// add match to new descriptions array list (linear will keep the data date
				// ordered)
				descriptions.add(collection.get(index));
			}
		}
		// new ImageAlbum instance which needs to be returned
		ImageAlbum descriptionCollection = new ImageAlbum(descriptions);
		return descriptionCollection;
	}

	/**
	 * Method to get RecordAlbum instance made up of an array list of Image Records
	 * with enum genre search matches.
	 * 
	 * @param type
	 * @return
	 */
	public ImageAlbum searchGenre(ImageType type) {
		// returns a reference to an ImageAlbum instance for
		// images which match type.
		// new array list to send to new instance of AlbumRecord
		ArrayList<ImageRecord> genres = new ArrayList<ImageRecord>();
		// iterate through the collection - linear search - better for multiple data
		// pieces
		for (int index = 0; collection.size() > index; index++) {
			// check for enum value matches enum value type
			if (collection.get(index).getGenre() == type) {
				// if match add to genres array list
				genres.add(collection.get(index));
			}
		}
		// return genre matching populated array list
		ImageAlbum genreCollection = new ImageAlbum(genres);
		return genreCollection;
	}

	/**
	 * Method to get RecordAlbum instance made up of an array list of Image Records
	 * with date range search matches.
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		// returns an ImageAlbum for
		// images taken between start and end dates.
		// new array list to send to new instance of AlbumRecord
		ArrayList<ImageRecord> dates = new ArrayList<ImageRecord>();
		// iterate through the collection - linear search - better for multiple data
		// pieces - find first date and add until the end date to the array list

		// check for order of dates
		if (end.isBefore(start)) {
			// swap dates using temp variable
			LocalDate temp = start;
			start = end;
			end = temp;
		}

		for (int index = 0; collection.size() > index; index++) {
			// check for date matches inside range given

			// variable for date
			LocalDate dateAtIndex = collection.get(index).getDate();

			// don't search any dates after end date (sorted data)
			if (dateAtIndex.isAfter(end)) {
				// break out of for after
				break;
			}

			if (!(dateAtIndex.isBefore(start) || dateAtIndex.isAfter(end))) {
				// if date in range add to dates array list
				dates.add(collection.get(index));
			}

		}

		// return ImageAlbum with all records date within the searched range.
		ImageAlbum datesCollection = new ImageAlbum(dates);
		return datesCollection;
	}

	/**
	 * Method to get RecordAlbum instance made up of an array list of all Image
	 * Records
	 * 
	 * @return
	 */
	public ImageAlbum getAllImages() {
		// returns a reference to an ImageAlbum instance for all images managed
		// by an ImageManager object.
		ImageAlbum all = new ImageAlbum(collection);
		return all;
	}

}
