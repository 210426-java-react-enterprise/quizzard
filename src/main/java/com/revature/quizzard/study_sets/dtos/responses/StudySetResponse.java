package com.revature.quizzard.study_sets.dtos.responses;

import com.revature.quizzard.study_sets.StudySet;
import com.revature.quizzard.flashcards.dtos.responses.FlashcardResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
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
