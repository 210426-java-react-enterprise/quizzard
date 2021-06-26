package com.revature.quizzard.study_sets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudySetRepository extends JpaRepository<StudySet, Integer> {
}
