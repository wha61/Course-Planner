package ca.cmpt213.a5.model;
/**
 * About class to provide elements in the "about.html"
 */
public class About {
    private String appName;
    private String authorName;

    public About(String appName, String authorName) {
        this.appName = appName;
        this.authorName = authorName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
