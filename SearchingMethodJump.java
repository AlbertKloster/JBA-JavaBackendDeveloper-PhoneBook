package phonebook;

public class SearchingMethodJump implements SearchingMethod {
    PhoneBook phoneBook = PhoneBook.getInstance();

    @Override
    public int findIndexByName(String name) {
        int n = phoneBook.getPhoneBook().size();
        int step = (int)Math.floor(Math.sqrt(n));

        int prev = 0;
        while (phoneBook.getPhoneBook().get(Math.min(step, n) - 1).getName().compareTo(name) < 0) {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }
        while (phoneBook.getPhoneBook().get(prev).getName().compareTo(name) < 0){
            prev++;

            if (prev == Math.min(step, n))
                return -1;
        }

        if (phoneBook.getPhoneBook().get(prev).getName().equals(name))
            return prev;

        return -1;

    }
}
