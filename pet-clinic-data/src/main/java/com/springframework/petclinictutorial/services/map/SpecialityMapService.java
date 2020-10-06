package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Specialty;
import com.springframework.petclinictutorial.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by sousaJ on 14/09/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Specialty, Long> implements SpecialityService {
    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object) {
        return super.save(object);
    }

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
