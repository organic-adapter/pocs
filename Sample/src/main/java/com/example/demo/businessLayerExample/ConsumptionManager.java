package com.example.demo.businessLayerExample;

import com.example.demo.model.Consumable;
import com.example.demo.model.Consumed;

public interface ConsumptionManager<T extends Consumable> {
    public Consumed<T> consume(T consumeMe) throws Exception;

    // #region Standard CRUD-like verbs.
    public T get(String id);

    public T save(T saveMe);

    public void Delete(String id);
    // #endregion
}
