# File Organizer by File Type
A simple Java program that organizes files in a directory into subfolders based on their file types (extensions). The program supports customizable file categories using an external configuration file, allowing you to modify the categories without recompiling the code.

## Features
File Sorting by Type: Automatically organizes files into categories like Images, Documents, Audio, Videos, etc., based on their extensions.
External Configuration: Uses a properties file (file-categories.properties) to define file type categories. The configuration is placed at ~/.config/fileorganizer/file-categories.properties, and if the file doesn't exist, a default configuration is created.
Cross-Platform: Designed to run on any platform with Java support, tested primarily on macOS.
Command-Line Usage: Easily run the program on any directory from the command line.
## Getting Started
### Prerequisites
Java Development Kit (JDK): Ensure you have JDK 8 or higher installed on your machine.

To check if Java is installed, run:

```
java -version
```

IntelliJ IDEA (optional): You can use any IDE or text editor to work with the project, but IntelliJ IDEA is recommended for development.

### Installation
Clone the repository:

```
git clone https://github.com/your-username/file-organizer.git
cd file-organizer
```

Compile the program: Assuming you are using a terminal/command line, run the following command to compile the project:

```
javac FileOrganizerWithExternalConfig.java
```

Create a JAR file: Package the compiled program into a JAR file for easier execution:

```
jar cfe fileorganizer.jar FileOrganizerWithExternalConfig FileOrganizerWithExternalConfig.class
```

Move the JAR to a location accessible globally (optional): If you want to run the program from anywhere in your terminal, move the JAR file to a location like /usr/local/bin:

```
sudo mv fileorganizer.jar /usr/local/bin/
```

### Configuration
The program relies on an external configuration file to categorize files based on their extensions. By default, the configuration file is stored at ~/.config/fileorganizer/file-categories.properties.

If this file does not exist, the program will automatically create it with default values. You can manually edit this file to add or modify file categories.

Example of file-categories.properties:

```
# Image files
jpg=Images
jpeg=Images
png=Images
gif=Images

# Audio files
mp3=Audio
wav=Audio

# Document files
pdf=Documents
doc=Documents
docx=Documents
txt=Documents

# Video files
mp4=Videos
avi=Videos
```

### Usage
To run the program, navigate to the directory you want to organize, or specify a directory as a command-line argument.

Running on the current directory:

```
java -jar /usr/local/bin/fileorganizer.jar
```

Running on a specific directory:

```
java -jar /usr/local/bin/fileorganizer.jar /path/to/directory
```

Editing the file categories: Modify the file at ~/.config/fileorganizer/file-categories.properties to add, remove, or change file categories. The program will automatically apply these changes on the next run.

### Example
Assume you have the following files in a directory:

```
document.pdf
picture.jpg
song.mp3
video.mp4
```

After running the program, it will create subfolders like Documents, Images, Audio, and Videos, and move the respective files into these folders.

### Troubleshooting
If the program isn't running as expected, ensure you have the correct Java version and check that the file-categories.properties file exists in the correct location.
Ensure you have write permissions in the directory you are organizing.
### Development
Development Environment: You can use IntelliJ IDEA or any Java-compatible IDE.
Modifying Configuration Location: If you want to change where the program looks for the configuration file, update the following line in FileOrganizerWithExternalConfig.java:
```
String configFilePath = System.getProperty("user.home") + "/.config/fileorganizer/file-categories.properties";
```
### Contributing
Feel free to fork this repository and submit pull requests with improvements or fixes. Please follow the established coding style and ensure that any added features include documentation.

### License
This project is licensed under the MIT License. See the LICENSE file for more details.
