package com.strongnguyen.nguontruyen.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/21/2018.
 * Email: vancuong2941989@gmail.com
 */
public class FilterBook {

    private String label;

    private List<Filter> filters; // danh sach thuoc tinh

    public FilterBook() {
        this("", new ArrayList<Filter>());
    }

    public FilterBook(String label) {
        this(label, new ArrayList<Filter>());
    }

    public FilterBook(String label, String path) {
        this(label, new ArrayList<Filter>());
    }

    public FilterBook(String label, List<Filter> filters) {
        this.label = label;
        this.filters = filters;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
