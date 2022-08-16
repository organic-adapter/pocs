package com.example.businessLayerExample;

import com.example.model.Bread;
import com.example.model.Consumed;
import com.example.resourceAccessLayerExample.BreadAccess;
import com.example.resourceAccessLayerExample.BreadInMemoryAccess.CannotFindKey;

public class BreadConsumptionManager implements ConsumptionManager<Bread> {

    private final BreadAccess access;

    public BreadConsumptionManager(BreadAccess access) {
        this.access = access;
    }

    /**
     * Rule #1, don't trust a client.
     * This should access the latest version prior to consuming.
     * If none is found, throw an exception that there is nothing to consume.
     * 
     * If the consumeMe.quantity is greater than the existing amount it deletes the record.
     * Otherwise it saves the difference.
     * 
     * It then returns a consumed response with the updated bread state.
     * 
     * @throws Exception
     */
    @Override
    public Consumed<Bread> consume(Bread consumeMe) throws Exception {
        Bread existing = get(consumeMe.getId());
        if (existing == null) {
            String errorMessage = String.format("Cannot consume if nothing exists. %s, %s", consumeMe.getId(),
                    consumeMe.getType());
            throw new Exception(errorMessage);
        }
        float remainder = existing.getQuantity() - consumeMe.getQuantity();
        Boolean deleteMe = remainder <= 0;
        String message = deleteMe ? "Yum! Finished it all." : "Oof. I'm stuffed. Can't eat another bite.";
        Bread eatenBread = new Bread(existing.getId(), existing.getType(), existing.getDisplayName(),
                Math.max(0, remainder));
        Consumed<Bread> returnMe = new Consumed<Bread>(eatenBread, message, deleteMe);

        if (deleteMe) {
            access.delete(eatenBread.getId());
            return returnMe;
        }

        access.save(eatenBread);
        return returnMe;
    }

    @Override
    public Bread get(String id) {
        try {
            return access.get(id);
        } catch (CannotFindKey cfk) {
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Bread save(Bread saveMe) {
        return access.save(saveMe);
    }

    @Override
    public void Delete(String id) {
        access.delete(id);
    }
}
