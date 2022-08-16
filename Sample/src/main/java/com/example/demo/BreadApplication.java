package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.businessLayerExample.BreadConsumptionManager;
import com.example.demo.businessLayerExample.ConsumptionManager;
import com.example.demo.configuration.MssqlConfig;
import com.example.demo.configuration.PostgreSqlConfig;
import com.example.demo.model.Bread;
import com.example.demo.resourceAccessLayerExample.BreadAccess;
import com.example.demo.resourceAccessLayerExample.BreadMssqlAccess;
import com.example.demo.resourceAccessLayerExample.BreadPostgreSqlAccess;

@SpringBootApplication
@RestController
public class BreadApplication {
	// Need to DI next.
	private final ConsumptionManager<Bread> manager;
	private final BreadAccess access;

	public BreadApplication() {
		access = new BreadMssqlAccess(MssqlConfig.GetConnectionString());
		//access = new BreadPostgreSqlAccess(PostgreSqlConfig.GetConnectionString());
		manager = new BreadConsumptionManager(access);
	}

	public static void main(String[] args) {
		SpringApplication.run(BreadApplication.class, args);
	}

	@GetMapping("/bread")
	public Bread getBread(@RequestParam(value = "id", defaultValue = "DemoUnique") String id) {
		return manager.get((id));
	}
}