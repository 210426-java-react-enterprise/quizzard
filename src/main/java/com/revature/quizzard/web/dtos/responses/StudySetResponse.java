package com.revature.quizzard.web.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.models.StudySet;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.stream.Collectors;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudySetResponse {

    private int id;
    private String name;
    private List<FlashcardResponse> studySetFlashCards;

    public StudySetResponse(StudySet studySet) {
        this.id = studySet.getId();
        this.name = studySet.getName();
        this.studySetFlashCards = studySet.getFlashcards().stream().map(FlashcardResponse::new).collect(Collectors.toList());
    }

}
