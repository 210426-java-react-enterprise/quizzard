package com.revature.quizzard.util.converters;

import com.revature.quizzard.flashcards.Flashcard;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Flashcard.Category, String> {

    @Override
    public String convertToDatabaseColumn(Flashcard.Category category) {
        if (category == null) {
            throw new IllegalArgumentException();
        }
        return category.toString();
    }

    @Override
    public Flashcard.Category convertToEntityAttribute(String categoryName) {
        return Stream.of(Flashcard.Category.values())
                     .filter(c -> c.toString().equals(categoryName))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new);
    }

}
