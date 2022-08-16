package com.example.resourceAccessLayerExample;

import java.util.HashMap;
import java.util.Map;

import com.example.model.Bread;

/**
 * Strictly for demonstrative purposes. Lambda's die after the function
 * completes so you can't check the persistence between calls.
 */
public class BreadInMemoryAccess implements BreadAccess, InMemoryAccess {
    private final Map<String, Bread> repository;

    public BreadInMemoryAccess() {
        repository = new HashMap<String, Bread>();
        repository.put("DemoUnique", new Bread("DemoUnique", "DemoBread", "This is Demo Bread", 5));
        repository.put("ABC123", new Bread("ABC123", "MilletBread", "Millet Bread", 27));
    }

    @Override
    public Bread get(String id) throws CannotFindKey {
        if (!repository.containsKey((id)))
            throw new CannotFindKey();
        return repository.get(id);
    }

    @Override
    public Bread save(Bread bread) {
        repository.put(bread.getId(), bread);
        return bread;
    }

    @Override
    public void delete(String id) {
        repository.remove(id);
    }

    public class CannotFindKey extends Exception {
    }
}
