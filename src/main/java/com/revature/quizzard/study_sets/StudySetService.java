package com.revature.quizzard.study_sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudySetService {

    private StudySetRepository studySetRepo;

    @Autowired
    public StudySetService(StudySetRepository studySetRepo) {
        this.studySetRepo = studySetRepo;
    }

}
