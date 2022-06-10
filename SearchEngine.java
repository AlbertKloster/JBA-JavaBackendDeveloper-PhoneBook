package phonebook;

public class SearchEngine {

    private SearchingMethod method;

    public void setMethod(SearchingMethod method) {
        this.method = method;
    }

    public int findIndexByName(String name) {
        return method.findIndexByName(name);
    }
}
