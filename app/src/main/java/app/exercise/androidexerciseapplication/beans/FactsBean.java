package app.exercise.androidexerciseapplication.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Data holder bean class
 */
public class FactsBean {

    private String title;

    @SerializedName("rows")
    private ArrayList<FactsItem> factsList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FactsItem> getFactsList() {
        return factsList;
    }

    public void setFactsList(ArrayList<FactsItem> factsList) {
        this.factsList = factsList;
    }

    public static class FactsItem {
        private String title, description, imageHref;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }
    }

}
