package com.revature.quizzard.web.dtos;

import com.revature.quizzard.models.StudySet;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudySetDTO {

    private int id;

    @NotEmpty
    private String name;

    private List<FlashcardDTO> studySetFlashCards;

    public StudySetDTO() {
        super();
        this.studySetFlashCards = new ArrayList<>();
    }

    public StudySetDTO(StudySet studySet) {
        this.id = studySet.getId();
        this.name = studySet.getName();
        this.studySetFlashCards = studySet.getFlashcards().stream().map(FlashcardDTO::new).collect(Collectors.toList());
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

    public List<FlashcardDTO> getStudySetFlashCards() {
        return studySetFlashCards;
    }

    public void setStudySetFlashCards(List<FlashcardDTO> studySetFlashCards) {
        this.studySetFlashCards = studySetFlashCards;
    }

    @Override
    public String toString() {
        return "StudySetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studySetFlashCards=" + studySetFlashCards +
                '}';
    }

}
