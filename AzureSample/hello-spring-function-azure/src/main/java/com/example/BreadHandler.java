package com.example;

import com.example.businessLayerExample.BreadConsumptionManager;
import com.example.businessLayerExample.ConsumptionManager;
import com.example.model.Bread;
import com.example.resourceAccessLayerExample.BreadAccess;
import com.example.resourceAccessLayerExample.BreadInMemoryAccess;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class BreadHandler extends FunctionInvoker<Bread, Bread> {
    @FunctionName("bread")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = { HttpMethod.GET, HttpMethod.PUT,
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Bread> request,
            ExecutionContext context) {
        // Figure out dependency injection next
        BreadAccess access = new BreadInMemoryAccess();
        ConsumptionManager<Bread> manager = new BreadConsumptionManager(access);
        // I'm not too keen on doing method routing by hand. But to quickly get an Azure
        // Function out this is what we are doing.
        Bread bread = null;
        if (request.getHttpMethod() == HttpMethod.GET) {
            String id = request.getQueryParameters().getOrDefault("id", "DemoUnique");
            bread = manager.get(id);
        }
        else if (request.getHttpMethod() == HttpMethod.POST || request.getHttpMethod() == HttpMethod.POST) {
            Bread body = request.getBody();
            manager.save(body);
        }
        return request
            .createResponseBuilder(HttpStatus.OK)
            .body(bread)
            .header("Content-Type", "application/json")
            .build();
    }
}
