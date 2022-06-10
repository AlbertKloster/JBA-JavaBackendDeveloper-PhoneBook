package phonebook;

public class SortEngine {

    private SortingMethod method;

    public void setMethod(SortingMethod method) {
        this.method = method;
    }

    public boolean sort() {
        return method.sort();
    }
}
