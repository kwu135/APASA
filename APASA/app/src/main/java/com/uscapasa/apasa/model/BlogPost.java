package com.uscapasa.apasa.model;

/**
 * BlogPost
 *
 * Represents a blog post on the USC APASA page
 * Contains only the title and its text contents
 */
public class BlogPost {

    private String mTitle;
    private String mContent;

    public BlogPost() {
        super();
        mTitle = "default title";
        mContent = "default content";
    }

    public BlogPost(String title, String content) {
        super();
        mTitle = title;
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }
}
