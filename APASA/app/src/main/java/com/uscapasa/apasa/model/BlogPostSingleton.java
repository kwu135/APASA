package com.uscapasa.apasa.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 2/16/2016.
 */
public class BlogPostSingleton {

    private static BlogPostSingleton sBlogPostSingleton;
    private ArrayList<BlogPost> mBlogPosts;

    private BlogPostSingleton() {
        super();
        mBlogPosts = new ArrayList<BlogPost>();
    }

    public static BlogPostSingleton getInstance() {
        if (sBlogPostSingleton == null) {
            sBlogPostSingleton = new BlogPostSingleton();
        }
        return sBlogPostSingleton;
    }

    public ArrayList<BlogPost> getBlogPosts() {
        return mBlogPosts;
    }

    public void setBlogPosts(ArrayList<BlogPost> blogPosts) {
        mBlogPosts = blogPosts;
    }

}
