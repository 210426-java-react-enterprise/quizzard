package com.revature.quizzard.study_sets;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/study-sets")
@CrossOrigin(origins = "*")
public class StudySetController {
}
