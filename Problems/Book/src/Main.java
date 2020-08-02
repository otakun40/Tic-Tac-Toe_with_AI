class Book {

    private String title;
    private int yearOfPublishing;
    private String[] authors;

    void setTitle(String title) {
        this.title = title;
    }

    void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    void setAuthors(String[] authors) {
        this.authors = authors.clone();
    }

    String getTitle() {
        return title;
    }

    int getYearOfPublishing() {
        return yearOfPublishing;
    }

    String[] getAuthors() {
        return authors.clone();
    }
}