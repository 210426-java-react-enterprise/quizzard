package com.revature.quizzard.models;

//import com.revature.quizzard.util.structures.LinkedList;
//import com.revature.quizzard.util.structures.List;

import org.hibernate.annotations.ForeignKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component("studyset")
public class StudySet {


    @Value("id")
    private int id;


    @Value("name")
    private String name;


    @Value("flashcards")
    private List<Flashcard> flashcards;


    private AppUser owner; //dependency

    public StudySet() {
    }

    public StudySet(String name, List<Flashcard> flashcards) {
        super();
        this.name = name;
        this.flashcards = flashcards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }


    public StudySet addFlashcard(Flashcard flashcard) {
        if (flashcards == null) {
            flashcards = new LinkedList<>();
        }
        flashcards.add(flashcard);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudySet studySet = (StudySet) o;
        return id == studySet.id && Objects.equals(name, studySet.name) && Objects.equals(flashcards, studySet.flashcards) && Objects.equals(owner, studySet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, flashcards, owner);
    }

    @Override
    public String toString() {
        return "StudySet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flashcards=" + flashcards +
                ", owner=" + owner +
                '}';
    }

}
