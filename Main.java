package phonebook;

import java.util.List;

public class Main {
    private static final String filePhoneBook = "./directory.txt";
    private static final String fileSearchList = "./find.txt";

    public static void main(String[] args) {
        initPhoneBook();
        List<String> searchList = initSearchList();
        SortEngine sortEngine = new SortEngine();
        SearchEngine searchEngine = new SearchEngine();

        // linear search
        searchEngine.setMethod(new SearchingMethodLinear());
        System.out.println("Start searching (linear search)...");
        long startTimeLinear = System.currentTimeMillis();
        long found = 0L;
        for (String name : searchList)
            if (searchEngine.findIndexByName(name) > 0)
                found++;
        long finishTimeLinear = System.currentTimeMillis();

        System.out.printf("Found %d / %d entries. Time taken: %s\n",
                found,
                searchList.size(),
                parseMillisToString(finishTimeLinear - startTimeLinear));

        // bubble sort + jump search
        initPhoneBook();
        searchList = initSearchList();

        searchEngine.setMethod(new SearchingMethodJump());
        System.out.println("\nStart searching (bubble sort + jump search)...");
        long startTimeSortBubble = System.currentTimeMillis();
        sortEngine.setMethod(new SortingMethodBubble(startTimeSortBubble, finishTimeLinear - startTimeLinear));

        boolean isSorted = sortEngine.sort();
        long finishTimeSortBubble = System.currentTimeMillis();
        if (isSorted) {
            long startTimeJump = System.currentTimeMillis();
            found = 0L;
            for (String name : searchList)
                if (searchEngine.findIndexByName(name) > 0)
                    found++;
            long finishTimeJump = System.currentTimeMillis();

            System.out.printf("Found %d / %d entries. Time taken: %s\n",
                    found,
                    searchList.size(),
                    parseMillisToString(finishTimeJump - startTimeSortBubble));
            System.out.printf("Sorting time: %s\n", parseMillisToString(finishTimeSortBubble - startTimeSortBubble));
            System.out.printf("Searching time: %s\n", parseMillisToString(finishTimeJump - startTimeJump));
        } else {
            // linear search
            initPhoneBook();
            searchList = initSearchList();

            searchEngine.setMethod(new SearchingMethodLinear());
            startTimeLinear = finishTimeSortBubble;
            found = 0L;
            for (String name : searchList)
                if (searchEngine.findIndexByName(name) > 0)
                    found++;
            finishTimeLinear = System.currentTimeMillis();

            System.out.printf("Found %d / %d entries. Time taken: %s\n",
                    found,
                    searchList.size(),
                    parseMillisToString(finishTimeLinear - startTimeSortBubble));

            System.out.printf("Sorting time: %s - STOPPED, moved to linear search\n",
                    parseMillisToString(finishTimeSortBubble - startTimeSortBubble));

            System.out.printf("Searching time: %s\n", parseMillisToString(finishTimeLinear - startTimeLinear));
        }

        // quick sort + binary search
        initPhoneBook();
        searchList = initSearchList();

        searchEngine.setMethod(new SearchingMethodJump());
        System.out.println("\nStart searching (quick sort + binary search)...");
        long startTimeQuickSort = System.currentTimeMillis();
        sortEngine.setMethod(new SortingMethodQuickSort());
        sortEngine.sort();
        long finishTimeQuickSort = System.currentTimeMillis();
        found = 0L;
        long startTimeBinary = finishTimeQuickSort;
        for (String name : searchList)
            if (searchEngine.findIndexByName(name) > 0)
                found++;
        long finishTimeBinary = System.currentTimeMillis();

        System.out.printf("Found %d / %d entries. Time taken: %s\n",
                found,
                searchList.size(),
                parseMillisToString(finishTimeBinary - startTimeQuickSort));
        System.out.printf("Sorting time: %s\n", parseMillisToString(finishTimeQuickSort - startTimeQuickSort));
        System.out.printf("Searching time: %s\n", parseMillisToString(finishTimeBinary - startTimeBinary));

        // creat hash table + hash search
        initPhoneBook();
        searchList = initSearchList();
        SearchMethodHashTable searchMethodHashTable = new SearchMethodHashTable();

        System.out.println("\nStart searching (hash table)...");
        long creatingTime = searchMethodHashTable.createHashMap();
        long startSearchingTime = System.currentTimeMillis();
        for (String name : searchList)
            if (searchEngine.findIndexByName(name) > 0)
                found++;
        long finishSearchingTime = System.currentTimeMillis();

        System.out.printf("Found %d / %d entries. Time taken: %s\n",
                found,
                searchList.size(),
                parseMillisToString(creatingTime + finishSearchingTime - startSearchingTime));
        System.out.printf("Creating time: %s\n", parseMillisToString(creatingTime));
        System.out.printf("Searching time: %s\n", parseMillisToString(finishSearchingTime - startSearchingTime));

    }

    private static String parseMillisToString(long millis) {
        return String.format("%d min. %d sec. %d ms.",
                millis / 60_000,
                (millis % 60_000) / 1000,
                millis % 1000);
    }

    private static void initPhoneBook() {
        PhoneBook phoneBook = PhoneBook.getInstance();
        FileHandler.loadPhoneBook(filePhoneBook, phoneBook);
    }

    private static List<String> initSearchList() {
        return FileHandler.loadSearchList(fileSearchList);
    }

}
