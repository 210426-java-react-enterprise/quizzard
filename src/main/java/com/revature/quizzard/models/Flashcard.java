package com.revature.quizzard.models;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "flashcards")
public class Flashcard {

//    @Id
//    @Column(name = "flashcard_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(nullable = false)
    private String question;

//    @Column(nullable = false)
    private String answer;

//    @Enumerated(EnumType.STRING)
    private Category category;

    public Flashcard() {
        super();
    }

    public Flashcard(String question, String answer, Category category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return id == flashcard.id && Objects.equals(question, flashcard.question) && Objects.equals(answer, flashcard.answer) && category == flashcard.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, category);
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", category=" + category +
                '}';
    }
}
