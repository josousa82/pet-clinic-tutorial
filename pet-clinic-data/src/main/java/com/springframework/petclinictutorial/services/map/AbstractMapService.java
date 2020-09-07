package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.BaseEntity;

import java.util.*;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new LinkedHashMap<>();

    Set<T> findAll(){
        return new LinkedHashSet<>(map.values());
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

    private Long getNextId(){
        Long nextId = null;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        }catch (NoSuchElementException e){
            nextId = 1L;
        }
       return nextId;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

}
