package com.strongnguyen.nguontruyen;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void expTest() throws MalformedURLException, URISyntaxException {
        String link = "https://truyenfull.vn/danh-sach/truyen-hot/";
        URL url = new URL(link);
        String[] paths = url.getPath().split("/");
        System.out.println("path url = " + paths[2]);
    }
}