package com.revature.quizzard.repositories;

import com.revature.quizzard.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {

    List<Flashcard> findFlashcardsByCategory(String category);
    List<Flashcard> findFlashcardsByCreatorId(int creatorId);

}
