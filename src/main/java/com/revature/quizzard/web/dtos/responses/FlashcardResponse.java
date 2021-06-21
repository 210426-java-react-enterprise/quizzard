package com.revature.quizzard.web.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.models.Flashcard;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlashcardResponse {

    private int id;
    private String question;
    private String answer;
    private int creatorId;
    private Flashcard.Category category;

    public FlashcardResponse(Flashcard flashcard) {
        this.id = flashcard.getId();
        this.question = flashcard.getQuestion();
        this.answer = flashcard.getAnswer();
        this.creatorId = flashcard.getCreator().getId();
        this.category = flashcard.getCategory();
    }

}

