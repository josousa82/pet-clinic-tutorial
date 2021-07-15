package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.BaseEntity;

import java.util.*;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        if(Objects.nonNull(object)) {
            if (Objects.isNull(object.getId())){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }else
            throw new RuntimeException("Object cannot be null");
        return object;
    }


    void deleteById(ID id){
        // TODO Implement check for wrong ID and throw invalid id custom exception, Not Found
        map.remove(id);
    }

    void delete(T object){
        // TODO Implement check for object with wrong ID and throw Not found exception
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    //TODO implement abstract update method

    protected Long getNextId(){
        Long nextId = null;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        }catch (NoSuchElementException e){
            nextId = 1L;
        }
        return nextId;
    }

}
