package edu.dlsu.mobapde.labfirebaseposts;

/**
 * Created by courtneyngo on 20/11/2017.
 */

public class Post {

    public static final String EXTRA_KEY = "key";

    private String uid;
    private String username;
    private String post;

    public Post(){}

    public Post(String username, String post) {
        this.username = username;
        this.post = post;
    }

    public Post(String uid, String username, String post) {
        this.uid = uid;
        this.username = username;
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
