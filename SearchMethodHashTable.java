package phonebook;

import java.util.HashMap;
import java.util.Map;

public class SearchMethodHashTable implements SearchingMethod {

    PhoneBook phoneBook = PhoneBook.getInstance();

    @Override
    public int findIndexByName(String name) {
        return 0;
    }

    public long createHashMap() {
        long startTime = System.currentTimeMillis();
        Map<String, Integer> hashMap = new HashMap<>();
        phoneBook.getPhoneBook().forEach(entry -> hashMap.put(entry.getName(), entry.getNumber()));
        return System.currentTimeMillis() - startTime;
    }

}
