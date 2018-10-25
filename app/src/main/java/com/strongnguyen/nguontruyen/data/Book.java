package com.strongnguyen.nguontruyen.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/21/2018.
 * Email: vancuong2941989@gmail.com
 */
@Entity(tableName = "book_table")
public class Book {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_book")
    private int idBook;

    @NonNull
    @ColumnInfo(name = "url")
    private String url;

    @Nullable
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "poster")
    private String poster;

    @Nullable
    @ColumnInfo(name = "author")
    private String author;

    @Nullable
    @ColumnInfo(name = "category")
    private String category;

    @Nullable
    @ColumnInfo(name = "description")
    private String description;

    @Nullable
    @ColumnInfo(name = "total_chapter")
    private String totalChapter;

    @Nullable
    @ColumnInfo(name = "first_chapter_url")
    private String firstChapterUrl;

    @ColumnInfo(name = "full")
    private boolean isFull;

    @ColumnInfo(name = "viewed")
    private int viewed;

    @ColumnInfo(name = "rating")
    private int rating;

    @ColumnInfo(name = "rating_count")
    private int ratingCount;

    @ColumnInfo(name = "last_read_at")
    private long lastReadAt;

    public Book() {
        url = "";
        title = "";
        poster = "";
        author = "";
        category = "";
        description = "";
        totalChapter = "0";
        firstChapterUrl = "";
    }

    public int getId() {
        return id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url.trim();
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @NonNull
    public String getPoster() {
        return poster;
    }

    public void setPoster(@NonNull String poster) {
        this.poster = poster.trim();
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    @Nullable
    public String getCategory() {
        return category;
    }

    public void setCategory(@Nullable String category) {
        this.category = category;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getTotalChapter() {
        return totalChapter;
    }

    public void setTotalChapter(@Nullable String totalChapter) {
        this.totalChapter = totalChapter;
    }

    @Nullable
    public String getFirstChapterUrl() {
        return firstChapterUrl;
    }

    public void setFirstChapterUrl(@Nullable String firstChapterUrl) {
        this.firstChapterUrl = firstChapterUrl;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public long getLastReadAt() {
        return lastReadAt;
    }

    public void setLastReadAt(long lastReadAt) {
        this.lastReadAt = lastReadAt;
    }
}
