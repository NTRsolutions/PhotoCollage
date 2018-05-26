package com.example.user.photocollage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImgurResponse {


    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("status")
    @Expose
    public Integer status;

    public static class Data {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("description")
        @Expose
        public Object description;
        @SerializedName("datetime")
        @Expose
        public Integer datetime;
        @SerializedName("cover")
        @Expose
        public String cover;
        @SerializedName("account_url")
        @Expose
        public String accountUrl;
        @SerializedName("account_id")
        @Expose
        public Integer accountId;
        @SerializedName("privacy")
        @Expose
        public String privacy;
        @SerializedName("layout")
        @Expose
        public String layout;
        @SerializedName("views")
        @Expose
        public Integer views;
        @SerializedName("link")
        @Expose
        public String link;
        @SerializedName("images_count")
        @Expose
        public Integer imagesCount;
        @SerializedName("images")
        @Expose
        public List<Image> images = null;

    }


    public static class Image {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public Object title;
        @SerializedName("description")
        @Expose
        public Object description;
        @SerializedName("datetime")
        @Expose
        public Integer datetime;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("animated")
        @Expose
        public Boolean animated;
        @SerializedName("width")
        @Expose
        public Integer width;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("size")
        @Expose
        public Integer size;
        @SerializedName("views")
        @Expose
        public Integer views;
        @SerializedName("bandwidth")
        @Expose
        public Integer bandwidth;
        @SerializedName("link")
        @Expose
        public String link;

    }
}