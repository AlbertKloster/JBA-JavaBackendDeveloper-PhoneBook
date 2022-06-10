package phonebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Class
 */
public class PhoneBook {

    private static PhoneBook instance;

    private List<Entry> phoneBook = new ArrayList<>();

    private PhoneBook() {}

    public static PhoneBook getInstance() {
        if (instance == null)
            instance = new PhoneBook();
        return instance;
    }

    public void addEntry(Entry entry) {
        phoneBook.add(entry);
    }

    public List<Entry> getPhoneBook() {
        return phoneBook;
    }

    public void clean() {
        phoneBook = new ArrayList<>();
    }

}
