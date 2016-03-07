package com.uscapasa.apasa.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BlogPost
 *
 * Represents a blog post on the USC APASA page
 * Contains only the title and its contents and its date
 */
public class BlogPost {

    private String mTitle;
    private String mContent;
    private Date mDate;

    public BlogPost() {
        super();
        mTitle = "default title";
        mContent = "default content";
        mDate = new Date();
    }

    public BlogPost(String title, String content, Date date) {
        super();
        mTitle = title;
        mContent = content;
        mDate = date;
    }

    public BlogPost(JSONObject jsonObject) {
        super();
        try {
            JSONObject titleObject = jsonObject.getJSONObject("title");
            mTitle = titleObject.getString("rendered");
            JSONObject contentObject = jsonObject.getJSONObject("content");
            mContent = contentObject.getString("rendered");
            try {
                String DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
                DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
                mDate = formatter.parse(jsonObject.getString("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() { return mDate; }
}
