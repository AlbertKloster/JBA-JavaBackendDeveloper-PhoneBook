package phonebook;

import java.util.List;

public class SortingMethodQuickSort implements SortingMethod {
    PhoneBook phoneBook = PhoneBook.getInstance();

    @Override
    public boolean sort() {
        quickSort(phoneBook.getPhoneBook(), 0, phoneBook.getPhoneBook().size() - 1);
        return true;
    }

    private static void quickSort(List<Entry> phoneBook, int low, int high) {
        if (low < high)
            performQuickSort(phoneBook, low, high);
    }

    private static void performQuickSort(List<Entry> phoneBook, int low, int high) {
        int pi = partition(phoneBook, low, high);
        quickSort(phoneBook, low, pi - 1);
        quickSort(phoneBook, pi + 1, high);
    }

    private static int partition(List<Entry> phoneBook, int low, int high) {
        String pivot = phoneBook.get(high).getName();
        int i = (low - 1);
        for(int j = low; j <= high - 1; j++) {
            if (phoneBook.get(j).getName().compareTo(pivot) < 0) {
                i++;
                swap(phoneBook, i, j);
            }
        }
        swap(phoneBook, i + 1, high);
        return (i + 1);
    }

    private static void swap(List<Entry> phoneBook, int i, int j) {
        Entry temp = phoneBook.get(i);
        phoneBook.set(i, phoneBook.get(j));
        phoneBook.set(j, temp);
    }
}
