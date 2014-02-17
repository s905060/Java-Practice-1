/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author jashlee
 */
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import com.google.gson.Gson;

public class Javaapplication1 {

    public static void main(String[] args) throws java.io.IOException {
        //  prompt the user to enter text string
        System.out.print("Please Enter Keyword: ");
        //  open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String Keyword = null;

        //  read the Keyword from the command-line; need to use try/catch with the
        //  readLine() method
        try {
            Keyword = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read Keyword!");
            System.exit(1);
        }

        System.out.println("");

        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = Keyword;
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        // Show title and URL of 1st result.
        System.out.println("The Title of first result:");
        System.out.println(results.getResponseData().getResults().get(0).getTitle() + "\n");
        System.out.println("URL of the result:");
        System.out.println(results.getResponseData().getResults().get(0).getUrl());

    }
}

class GoogleResults {

    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "ResponseData[" + responseData + "]";
    }

    static class ResponseData {

        private List<Result> results;

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            return "Results[" + results + "]";
        }
    }

    static class Result {

        private String url;
        private String title;

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Result[url:" + url + ",title:" + title + "]";
        }
    }
}