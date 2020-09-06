package com.springframework.petclinictutorial.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(ID id, T object){
        map.put(id, object);
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    T findByLastName(String name){
        return null;
    }
}
