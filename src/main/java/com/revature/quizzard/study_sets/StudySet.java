package com.revature.quizzard.study_sets;

import com.revature.quizzard.flashcards.Flashcard;
import com.revature.quizzard.users.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor
@Entity @Table(name = "study_sets")
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

}
