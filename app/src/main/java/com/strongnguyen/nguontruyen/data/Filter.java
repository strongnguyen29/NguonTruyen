package com.strongnguyen.nguontruyen.data;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/22/2018.
 * Email: vancuong2941989@gmail.com
 */
public class Filter {

    private String title; // vd: truyen hot, tien hiep

    private String value; // url seo: truyen-hot, tien-hiep

    public Filter() {
        this("", "");
    }

    public Filter(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
