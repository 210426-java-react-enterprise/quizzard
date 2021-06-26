package com.revature.quizzard.users;

import com.fasterxml.jackson.annotation.JsonValue;
import com.revature.quizzard.flashcards.Flashcard;
import com.revature.quizzard.study_sets.StudySet;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data @NoArgsConstructor
@Entity @Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<StudySet> userStudySets;

    @OneToMany(mappedBy = "creator")
    private List<Flashcard> userFlashcards;

    @ManyToMany
    @JoinTable(
            name = "user_favorited_flashcards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "flashcard_id")
    )
    private List<Flashcard> favoritedFlashcards;

    @ManyToMany
    @JoinTable(
            name = "user_flagged_flashcards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "flashcard_id")
    )
    private List<Flashcard> flaggedFlashcards;

    public AppUser(int id) {
        this.id = id;
    }

    public AppUser(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public enum Role {
        ADMIN, DEV, BASIC_USER, PREMIUM_USER, LOCKED;
    }

}
