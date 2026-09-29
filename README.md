# Java Image Management System

## Overview

This project was developed as part of the Object-Oriented Programming (CSC1029) module during the first year of the BSc Computing and Information Technology degree at Queen's University Belfast.

The application manages collections of photographic images through a menu-driven interface, allowing users to create, search, browse, and display image records across a range of image genres. The project demonstrates object-oriented design principles, data management, searching and filtering, exception handling, and user interface development in Java.

---

## Project Status


Completed Coursework Project

 
Completed as part of the CSC1029 Object-Oriented Programming module at Queen's University Belfast.

Academic year: 2025-26


## Project Context

The project was completed individually as coursework for the Object-Oriented Programming module.

The assignment required the development of:

- Core object-oriented classes to manage image records and image albums.
- A menu-driven image management application.
- Advanced functionality using a custom Java Console library.
- Support for image display, colour customisation, and enhanced user interaction.

---

## Features

### Image Management

- Create and store image records.
- Automatically assign unique image identifiers.
- Manage photographic images across multiple genres.
- Store image metadata including title, description, date, genre, and thumbnail reference.

### Search Functionality

Search images by:

- Unique ID
- Title
- Description
- Genre- Date range

Search results are returned as image collections that can be browsed interactively. 

### Album Navigation

- Browse image collections sequentially.
- Move forwards and backwards through albums.
- View image details individually.
- Images maintained in date order. 

### User Interface

Using the custom Console library, the application includes:

- Colour-coded interfaces.
- Dynamic console resizing.
- Image thumbnail display.
- Enhanced menu navigation.
- Input validation and error feedback.

### Validation and Error Handling

- Integer validation.
- Date validation using `LocalDate`.
- File name validation.
- Genre validation using enumerations.
- Exception handling throughout the application.

---

## Class Structure

### ImageRecord

Represents an individual photographic image.
Stores:

- Unique ID
- Title
- Description
- Genre
- Date Taken
- Thumbnail Filename

### ImageManager

Manages all image records within the system.

Provides functionality to:

- Add images
- Search by ID
- Search by title
- Search by description
- Search by genre
- Search by date range

### ImageAlbum

Represents collections of images returned by searches.

Provides functionality to:

- Retrieve first image
- Navigate to next image
- Navigate to previous image
- Maintain chronological ordering

### Menu

Custom menu class used to:

- Display application menus
- Validate user input
- Improve code reuse

### QUBMediaImages

Main application controller responsible for:

- User interaction
- Menu management
- Search functionality
- Album navigation
- Image display
- Console customisation and visual enhancements.

---

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections
- LocalDate API
- Exception Handling
- Custom Console Library (CSC1029Console.jar)
- Eclipse IDE
- Git
- GitHub

---

## Skills Demonstrated
 
- Object-Oriented Programming (OOP)
- Encapsulation
- Inheritance
- Enumerations
- Exception Handling
- Collections and Data Management
- Searching and Filtering Algorithms
- User Input Validation
- Software Design Principles
- Version Control with Git
- Java Application Development

---

## Running the Application
 
The project can be opened and run in any Java IDE that supports Java 17 or later, including:
 
- Visual Studio Code
- Eclipse
- IntelliJ IDEA
 
### Part 1
 
Run the `QUBImages` class located in:
 
```text
src/part01/QUBImages.java
```
 
This version uses a standard terminal-based interface.
 
### Part 2
 
Run the `QUBMediaImages` class located in:
 
```text
src/part02/QUBMediaImages.java
```
 
This version uses the CSC1029 Console library to provide enhanced features including image display, colour customisation, and an improved user interface.
 
### Images
 
Sample images used by the application are included in the `Images` directory.

---

## My Contributions

This was an individual coursework project.

Key areas developed include:

- Object-oriented system design.
- ImageRecord, ImageManager and ImageAlbum implementation.
- Menu system architecture.
- Search and filtering functionality.
- Album navigation system.
- Custom console user interface.
- Colour themed displays based on image genres.
- Dynamic image rendering and display.
- Input validation and exception handling. turn16search2

---

## Key Learning Outcomes

Through this project I developed experience in:

- Object-oriented programming
- Classes and objects
- Encapsulation
- Constructors
- Enumerations
- Collections and data management
- Searching and filtering algorithms
- Exception handling
- User input validation
- Software design
- Java application development
- Code reuse and modular design

---

## Repository Structure

```text
src/
├── part01/
│   ├── ImageAlbum.java
│   ├── ImageManager.java
│   ├── ImageRecord.java
│   ├── ImageType.java
│   ├── Menu.java
│   └── QUBImages.java
│
├── part02/
│   ├── Menu.java
│   └── QUBMediaImages.java
│
lib/
├── CSC1029Console.jar
|
Images/
├── Andromeda.png
├── Apples.png
├── CentralPark.png
├── ChatGPT.png
├── Homer.png
├── KermitGolf.png
├── LanyonQUB.png
├── Meme.png
├── Mournes.png
└── RedKite.png
```

---

## Screenshots

### Main Menu

![Main Menu Screen](/Screenshots/Main_Menu.png)

### Search Menu

![Search menu screen](/Screenshots/Search_Menu.png)

### Album Navigation

![Album Navigation Screen](/Screenshots/Album_Nav.png)

### Image Display Interface

![Image Display Interface](/Screenshots/Image_Display.png)

---

## Future Improvements

Potential future enhancements include:

- Graphical user interface using JavaFX or Swing.
- Database integration.
- Image upload functionality.
- Advanced sorting options.
- Persistent file storage.
- Export and reporting functionality.
- Improved search filtering.
- User authentication and profiles

by F1shG3ck0

---

## Academic Note

This project was completed as part of the CSC1029 Object-Oriented Programming module at Queen's University Belfast and is included in this repository to demonstrate object-oriented software development, problem-solving, and Java programming skills.
