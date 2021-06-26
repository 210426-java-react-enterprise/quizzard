package com.revature.quizzard.flashcards.dtos.responses;

import com.revature.quizzard.flashcards.Flashcard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
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

