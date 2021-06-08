package com.revature.quizzard.web.dtos;

import com.revature.quizzard.models.Flashcard;

import javax.validation.constraints.NotEmpty;

public class FlashcardDTO {

    private int id;

    @NotEmpty
    private String question;

    @NotEmpty
    private String answer;

    private int creatorId;

    @NotEmpty
    private Flashcard.Category category;

    public FlashcardDTO() {
        super();
    }

    public FlashcardDTO(Flashcard flashcard) {
        this.id = flashcard.getId();
        this.question = flashcard.getQuestion();
        this.answer = flashcard.getAnswer();
        this.creatorId = flashcard.getCreator().getId();
        this.category = flashcard.getCategory();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Flashcard.Category getCategory() {
        return category;
    }

    public void setCategory(Flashcard.Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "FlashcardDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", category=" + category +
                '}';
    }
}

