package com.revature.quizzard.flashcards;

import com.revature.quizzard.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class FlashcardService {

    private FlashcardRepository cardRepo;

    @Autowired
    public FlashcardService(FlashcardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Transactional(readOnly = true)
    public List<Flashcard> getAllCards() {
        List<Flashcard> cards = cardRepo.findAll();

        if (cards.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return cards;

    }

    @Transactional(readOnly = true)
    public List<Flashcard> getCardsByCreatorId(int creatorId) {
        List<Flashcard> cards = cardRepo.findFlashcardsByCreatorId(creatorId);

        if (cards.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return cards;
    }

    @Transactional(readOnly = true)
    public List<Flashcard> getCardsByCategory(@NotNull Flashcard.Category category) {
        List<Flashcard> cards = cardRepo.findFlashcardsByCategory(category.toString());

        if (cards.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return cards;
    }

    @Transactional(readOnly = true)
    public Flashcard getCardById(@Min(0) int cardId) {
        return cardRepo.findById(cardId).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveNewCard(@Valid Flashcard newCard) {
        cardRepo.save(newCard);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateCard(@Valid Flashcard updatedCard) {
        getCardById(updatedCard.getId());
        cardRepo.save(updatedCard);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCardById(int cardId) {
        cardRepo.delete(getCardById(cardId));
    }

}

