package com.springframework.petclinictutorial.formatters;

import com.springframework.petclinictutorial.model.PetType;
import com.springframework.petclinictutorial.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by sousaJ on 23/01/2021
 * in package - com.springframework.petclinictutorial.formatters
 **/
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        for (PetType petType : findPetTypes){
            if (petType.getName().equals(s)){
                return petType;
            }
        }
        throw new ParseException("Type not found: " + s, 0);
    }

}
