package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Visit;
import com.springframework.petclinictutorial.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return new HashSet<>(super.findAll());
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        if (Objects.isNull(visit.getPet())
            || Objects.isNull(visit.getPet().getOwner())
            || Objects.isNull(visit.getPet().getId())
            || Objects.isNull(visit.getPet().getOwner().getId())){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
