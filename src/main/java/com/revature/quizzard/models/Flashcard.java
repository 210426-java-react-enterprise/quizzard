package com.revature.quizzard.models;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Component("flashcards")
@Entity
@Table(name = "flashcards")
public class Flashcard {

    @Id
    @Column(name = "flashcard_id")
    @Value("id")
    private int id;

    @Column(name = "question", nullable = false)
    @Value("${question}")
    private String question;

    @Column(name = "answer", nullable = false)
    @Value("${answer}")
    private String answer;

    private Category category; //dependency

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

    @Autowired //lets other devs know this is a dependency
    public Category getCategory() {
        return category;
    }

    @Autowired //lets other devs know this is a dependency
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
