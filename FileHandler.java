package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static void loadPhoneBook(String filename, PhoneBook phoneBook) {
        phoneBook.clean();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] numberAndName = scanner.nextLine().split("\\s+", 2);
                phoneBook.addEntry(new Entry(Integer.parseInt(numberAndName[0]), numberAndName[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filename);
        }
    }

    public static List<String> loadSearchList(String filename) {
        List<String>  searchList = new ArrayList<>();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                searchList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filename);
        }
        return searchList;
    }
}
