package phonebook;

public class SortingMethodBubble implements SortingMethod {
    PhoneBook phoneBook = PhoneBook.getInstance();

    private final long startTimeSortBubble;
    private final long linearSearchTime;

    public SortingMethodBubble(long startTimeSortBubble, long linearSearchTime) {
        this.startTimeSortBubble = startTimeSortBubble;
        this.linearSearchTime = linearSearchTime;
    }

    @Override
    public boolean sort() {
        int n = phoneBook.getPhoneBook().size();
        int i, j;
        Entry temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (phoneBook.getPhoneBook().get(j).getName().compareTo(phoneBook.getPhoneBook().get(j + 1).getName()) > 0) {
                    temp = phoneBook.getPhoneBook().get(j);
                    phoneBook.getPhoneBook().set(j, phoneBook.getPhoneBook().get(j + 1));
                    phoneBook.getPhoneBook().set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped)
                break;

            if ((System.currentTimeMillis() - startTimeSortBubble) > 10 * linearSearchTime)
                return false;
        }
        return true;
    }
}
