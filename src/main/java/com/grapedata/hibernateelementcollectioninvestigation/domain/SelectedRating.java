package com.grapedata.hibernateelementcollectioninvestigation.domain;

import javax.persistence.Embeddable;

@Embeddable
public class SelectedRating {

    private String optionId;
    private String ratingId;

    public SelectedRating() {
    }

    public SelectedRating(String optionId, String ratingId) {
        this.optionId = optionId;
        this.ratingId = ratingId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }
}
