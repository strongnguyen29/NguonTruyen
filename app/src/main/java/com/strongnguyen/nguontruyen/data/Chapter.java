package com.strongnguyen.nguontruyen.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/21/2018.
 * Email: vancuong2941989@gmail.com
 */
@Entity(tableName = "chapter_table")
public class Chapter {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_chapter")
    private long idChapter;

    @ColumnInfo(name = "id_book")
    private int idBook;

    @Nullable
    @ColumnInfo(name = "title")
    private String title;

    @Nullable
    @ColumnInfo(name = "url")
    private String url;

    @Nullable
    @ColumnInfo(name = "nextUrl")
    private String nextUrl;

    @Nullable
    @ColumnInfo(name = "prevUrl")
    private String prevUrl;

    @Nullable
    @ColumnInfo(name = "content")
    private String content;

    public Chapter() {
        title = "";
        url = "";
        nextUrl = "";
        prevUrl = "";
        content = "";
    }

    public int getId() {
        return id;
    }

    public long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(long idChapter) {
        this.idChapter = idChapter;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(@Nullable String nextUrl) {
        this.nextUrl = nextUrl;
    }

    @Nullable
    public String getPrevUrl() {
        return prevUrl;
    }

    public void setPrevUrl(@Nullable String prevUrl) {
        this.prevUrl = prevUrl;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }
}
