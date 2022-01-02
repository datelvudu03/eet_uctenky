import java.io.File;
import java.util.Scanner;

public class GUI {
    private String finalFile, filename, directory;

    public GUI() {
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(String finalFile) {
        this.finalFile = finalFile;
    }

    public String runToText() {
        String directory, fileName;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please specify the directory where you want to created the file.");
            System.out.println("Valid directory example: C:/Users/DAA/Desktop/ or C:\\Users\\DAA\\Desktop\\team\\");
            directory = scanner.nextLine();
            File file = new File(directory);
            if (file.exists()) {
                System.out.println("You typed:" + directory);
                System.out.println("The directory is valid.");
                scanner.reset();
                break;
            } else {
                System.out.println(directory);
                System.out.println("Invalid directory. Try again.");
            }
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of file that you want to create:");
            fileName = scanner.nextLine();
            File file = new File(directory + fileName + ".txt");
            if (file.exists()) {
                System.out.println("A file with the name -- " + fileName + " -- in " + directory + " already exists");
                System.out.println("Do you want to overwrite the existing file? Press y to overwrite. ");
                boolean overwrite = scanner.nextLine().equalsIgnoreCase("y");
                if (overwrite) {
                    finalFile = directory + fileName + ".txt";
                    System.out.println("The absolute path is: " + finalFile);
                    scanner.reset();
                    break;
                } else {
                    System.out.println("Try again.");
                }

            } else {
                System.out.println("There is no file under the name " + fileName + ".");
                System.out.println("File with the name -- " + fileName + " -- will be created in this directory: " + directory);
                finalFile = directory + fileName + ".txt";
                System.out.println("The absolute path is: " + finalFile);
                scanner.reset();
                break;
            }
        }
        setDirectory(directory);
        setFilename(fileName);
        setFinalFile(finalFile);
        return finalFile;
    }

    public String runToCVS() {
        String directoryForCVS = null,finalFileCSV = null;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to convert -- " + getFilename() + " -- to CVS format? Press y to continue.");
            boolean willConvert = scanner.nextLine().equalsIgnoreCase("y");
            if (willConvert) {
                System.out.println("Do you want to reuse the directory? Press y to reuse.");
                boolean willReuse = scanner.nextLine().equalsIgnoreCase("y");
                if (willReuse){
                    directoryForCVS = getDirectory();
                }else{
                    System.out.println("Please specify the directory where you want to created the file.");
                    System.out.println("Valid directory example: C:/Users/DAA/Desktop/ or C:\\Users\\DAA\\Desktop\\team\\");
                    directoryForCVS = scanner.nextLine();
                }

                File file = new File(directoryForCVS);
                if (file.exists()) {
                    System.out.println("The directory is: " + directoryForCVS);
                    System.out.println("The directory is valid.");
                    scanner.reset();
                    break;
                } else {
                    System.out.println(directoryForCVS);
                    System.out.println("Invalid directory. Try again.");
                }
            } else {
                System.out.println(getFilename() + " was not converted to CVS.");
                break;

            }
        }
        setFilename(getFilename()+"-output.csv");
        while (directoryForCVS != null) {
            Scanner scanner = new Scanner(System.in);
            File file = new File(directoryForCVS + getFilename());
            if (file.exists()) {
                System.out.println("A file with the name -- " + getFilename() + " -- in " + directoryForCVS + " already exists");
                System.out.println("Do you want to overwrite the existing file? Press y to overwrite. ");
                boolean overwrite = scanner.nextLine().equalsIgnoreCase("y");
                if (overwrite) {
                    finalFileCSV = directoryForCVS + getFilename();
                    System.out.println("The absolute path is: " + finalFileCSV);
                    scanner.reset();
                    break;
                } else {
                        System.out.println("Try again.");
                        System.out.println("Type the name of your file:");
                        setFilename(scanner.nextLine() + ".csv" );
                }

            } else {
                System.out.println("There is no file under the name -- " + getFilename() + " -- .");
                System.out.println("File with the name -- " + getFilename() + " -- will be created in this directory: " + directoryForCVS);
                finalFileCSV = directoryForCVS + getFilename();
                System.out.println("The absolute path is: " + finalFileCSV);
                scanner.reset();
                break;
            }
        }
        return finalFileCSV;
    }

}

