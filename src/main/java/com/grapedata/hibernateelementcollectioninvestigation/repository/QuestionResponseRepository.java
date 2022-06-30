package com.grapedata.hibernateelementcollectioninvestigation.repository;

import com.grapedata.hibernateelementcollectioninvestigation.domain.QuestionResponse;
import com.grapedata.hibernateelementcollectioninvestigation.domain.QuestionResponseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, QuestionResponseId> {
}
