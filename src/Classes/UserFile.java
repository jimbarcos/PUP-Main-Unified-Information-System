package Classes;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class UserFile {

    private String fName;
    private String fUsername;
    private String fPassword;
    private String fCode;
    private List<String> lines;

    public UserFile() {
        this.lines = new ArrayList<>();
    }
    
    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the fUsername
     */
    public String getfUsername() {
        return fUsername;
    }

    /**
     * @param fUsername the fUsername to set
     */
    public void setfUsername(String fUsername) {
        this.fUsername = fUsername;
    }

    /**
     * @return the fPassword
     */
    public String getfPassword() {
        return fPassword;
    }

    /**
     * @param fPassword the fPassword to set
     */
    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    /**
     * @return the fCode
     */
    public String getfCode() {
        return fCode;
    }

    /**
     * @param fCode the fCode to set
     */
    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public boolean isUsernameExist(String fUsername) {
        try {
            File accounts = new File("Account.txt");
            try (Scanner myReader = new Scanner(accounts)) {
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();

                    if (line.startsWith("Username: ") && line.split(": ")[1].equals(fUsername)) {
                        System.out.println("Username from file: " + line);
                        myReader.close();
                        return true;
                    }
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    // Method to check if backup code exist within a file.
    public boolean codeExist(String fCode) {
        try {
            File accounts = new File("Account.txt");
            try (Scanner myReader = new Scanner(accounts)) {
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();

                    if (line.startsWith("Back-up Code: ") && line.split(": ")[1].equals(fCode)) {
                        myReader.close();
                        return true;
                    }
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    // Method to check if password and username exist within a file.
    public boolean isUnamePassValid(String fUsername, String fPassword) {
        try {
            File accounts = new File("Account.txt");
            try (Scanner myReader = new Scanner(accounts)) {
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    
                    if (line.startsWith("Username: ") && line.split(": ")[1].equals(fUsername) && myReader.nextLine().split(": ")[1].equals(fPassword)) {
                        myReader.close();
                        return true;
                    }

                }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    // Method to add data in the file
    public boolean storeData(String fName, String fUsername, String fPassword, String fCode) {
        File accounts = new File("Account.txt");
        if (accounts.exists()) {
            try (FileWriter fileWriter = new FileWriter(accounts, true)) {
                fileWriter.write("Name: " + fName + "\n");
                fileWriter.write("Username: " + fUsername + "\n");
                fileWriter.write("Password: " + fPassword + "\n");
                fileWriter.write("Back-up Code: " + fCode + "\n\n");

                fileWriter.close();
            } catch (IOException e) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }

    public void updateDate(String fCode, String fUsername, String fPassword) {
        // List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Account.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            ListIterator<String> iterator = lines.listIterator();
            while (iterator.hasNext()) {
                String currentLine = iterator.next();

                if (currentLine.startsWith("Back-up Code:") && currentLine.split(": ")[1].equals(fCode)) {
                    int fIndexCode = iterator.previousIndex();

                    if (fIndexCode >= 2) {
                        lines.set(fIndexCode - 2, "Username: " + fUsername);
                        lines.set(fIndexCode - 1, "Password: " + fPassword);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UserFile.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("Account.txt"))) {
            for (String updatedLine : lines) {
                writer.println(updatedLine);
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }

    }
}
