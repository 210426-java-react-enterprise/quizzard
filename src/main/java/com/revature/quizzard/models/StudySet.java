package com.revature.quizzard.models;

//import com.revature.quizzard.util.structures.LinkedList;
//import com.revature.quizzard.util.structures.List;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class StudySet {


    @Id
    @Column(name = "studyset_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "studyset_name")
    private String name;

    @Column
    private List<Flashcard> flashcards;

    @Column
    private AppUser owner;

    @Autowired
    public StudySet(List<Flashcard> flashcards, AppUser owner) {
        super();
    }

    public StudySet(String name, List<Flashcard> flashcards) {
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
