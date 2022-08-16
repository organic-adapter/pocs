package com.example;

import com.example.businessLayerExample.BreadConsumptionManager;
import com.example.businessLayerExample.ConsumptionManager;
import com.example.model.Bread;
import com.example.model.Consumed;
import com.example.resourceAccessLayerExample.BreadAccess;
import com.example.resourceAccessLayerExample.BreadInMemoryAccess;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class BreadConsumptionHandler extends FunctionInvoker<Consumed<Bread>, Consumed<Bread>> {
    @FunctionName("consume") //How do we do custom routes?
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Bread> request,
            ExecutionContext context) {
        // Figure out dependency injection next
        BreadAccess access = new BreadInMemoryAccess();
        ConsumptionManager<Bread> manager = new BreadConsumptionManager(access);
        Bread body = request.getBody();
        try {
            Consumed<Bread> consumed = manager.consume(body);

            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body(consumed)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (Exception ex) {
            return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .build();
        }
    }
}
