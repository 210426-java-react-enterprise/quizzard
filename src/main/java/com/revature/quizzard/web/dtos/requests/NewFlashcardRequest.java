package com.revature.quizzard.web.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.models.Flashcard;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewFlashcardRequest {

    @NotEmpty private String question;
    @NotEmpty private String answer;
    @Min(1) private int creatorId;
    @NotEmpty private String category;

    public Flashcard toFlashcard() {
        return new Flashcard(question, answer, creatorId, category);
    }
}
