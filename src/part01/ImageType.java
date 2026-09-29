package part01;

/**
 * @author F1shG3ck0
 */
/*
 * enum - for image genre types.
 */

public enum ImageType {
	// ImageType options
	ASTRONOMY("Astronomy",
			"Photography or imaging of astronomical objects, celestial events, or areas of the night sky."),
	ARCHITECTURE("Architecture",
			"Focuses on the capture of images that accurately represent the design and feel of buildings."),
	SPORT("Sport", "Covers all types of sports and can be considered a branch of photojournalism."),
	LANDSCAPE("Landscape", "The study of the textured surface of the Earth and features images of natural scenes."),
	PORTRAIT("Portrait", "Images of a person or a group of people where the face and facial features are predominant."),
	NATURE("Nature", "Focused on elements of the outdoors including sky, water, and land, or the flora and fauna."),
	AERIAL("Aerial", "Images taken from an aircraft or other airborne platforms."),
	FOOD("Food",
			"Captures everything related to food, from fresh ingredients and plated dishes to the cooking process."),
	OTHER("Other", "Covers just about any other type of image and photography genre.");
	
	
	@SuppressWarnings("unused")
	// Variable to store image Description - implement in later use if necessary
	private String imageDescription;
	// Variable to store images name (well formatted for printing - toString use)
	private String imageName;

	// set variables for selected genre option
	private ImageType(String name, String desc) {
		this.imageName = name;
		this.imageDescription = desc;
	}

	// toString runs in print commands - prints out nicely written name with Cap
	// first letter.
	public String toString() {
		return imageName;
	}

}
