package com.revature.quizzard.flashcards.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.flashcards.Flashcard;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data @NoArgsConstructor
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
