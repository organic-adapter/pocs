package com.example.demo.resourceAccessLayerExample;

import com.example.demo.model.Bread;
import com.example.demo.resourceAccessLayerExample.exceptions.CannotFindKey;

public interface BreadAccess {
    Bread get(String id) throws CannotFindKey ;

    Bread save(Bread bread);

    void delete(String id);
}
