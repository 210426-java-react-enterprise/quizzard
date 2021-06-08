package com.revature.quizzard.models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "study_sets")
public class StudySet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "study_set_cards",
            joinColumns = @JoinColumn(name = "study_set_id"),
            inverseJoinColumns = @JoinColumn(name = "flashcard_id")
    )
    private List<Flashcard> flashcards;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    public StudySet() {
        super();
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
