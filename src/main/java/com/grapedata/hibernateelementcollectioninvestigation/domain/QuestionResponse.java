package com.grapedata.hibernateelementcollectioninvestigation.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question_response")
public class QuestionResponse {

    @EmbeddedId
    private QuestionResponseId id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "question_response_ratings",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
                    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
            })
    @AttributeOverrides({
            @AttributeOverride(name = "optionId", column = @Column(name = "option_id", nullable = false)),
            @AttributeOverride(name = "ratingId", column = @Column(name = "rating_id", nullable = false))
    })
    private List<SelectedRating> selectedRatings;

    public QuestionResponseId getId() {
        return id;
    }

    public void setId(QuestionResponseId id) {
        this.id = id;
    }

    public List<SelectedRating> getSelectedRatings() {
        return selectedRatings;
    }

    public void setSelectedRatings(List<SelectedRating> selectedRatings) {
        this.selectedRatings = selectedRatings;
    }
}
