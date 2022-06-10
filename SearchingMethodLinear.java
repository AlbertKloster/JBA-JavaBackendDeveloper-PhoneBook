package phonebook;

public class SearchingMethodLinear implements SearchingMethod {
    PhoneBook phoneBook = PhoneBook.getInstance();

    @Override
    public int findIndexByName(String name) {
        for (int i = 0; i < phoneBook.getPhoneBook().size(); i++) {
            if (name.equals(phoneBook.getPhoneBook().get(i).getName()))
                return i;
        }
        return -1;
    }
}
