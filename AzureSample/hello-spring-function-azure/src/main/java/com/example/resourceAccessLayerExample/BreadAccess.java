package com.example.resourceAccessLayerExample;

import com.example.model.Bread;
import com.example.resourceAccessLayerExample.BreadInMemoryAccess.CannotFindKey;

public interface BreadAccess {
    Bread get(String id) throws CannotFindKey ;

    Bread save(Bread bread);

    void delete(String id);
}
