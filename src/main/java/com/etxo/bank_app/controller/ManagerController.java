package com.etxo.bank_app.controller;


import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.exceptions.ErrorResponse;
import com.etxo.bank_app.exceptions.ManagerNotFoundException;
import com.etxo.bank_app.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Set;

@RestController
@RequestMapping("api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    @Operation(summary = "creates manager",
            description = "the manager may not exist in the database")
    @ApiResponse(responseCode = "226",
            description = "manager already exists")
    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<ManagerDto> create(
            @RequestBody @Valid ManagerDto manager){

        return ResponseEntity.ok(service.create(manager));
    }

    @Operation(summary = "gets all managers")
    @GetMapping
    @Secured({"ADMIN", "MANAGER"})
    public ResponseEntity<Set<ManagerDto>> getAll(){

        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "gets manager by ID",
            description = "the manager should exist in the database")
    @ApiResponse(responseCode = "404",
            description = "manager not found")
    @GetMapping("/{id}")
    @Secured({"ADMIN", "MANAGER"})
    public ResponseEntity<ManagerDto> getManagerById(
            @PathVariable Long id) {

            return ResponseEntity.ok(service.getManagerById(id));
    }

    @Operation(summary = "updates manager by ID",
            description = "the manager should exist in the database")
    @ApiResponse(responseCode = "404",
            description = "manager not found")
    @PutMapping("/{id}")
    @Secured("MANAGER")
    public ResponseEntity<ManagerDto> update(@PathVariable Long id, @RequestBody ManagerDto dto){

        return ResponseEntity.ok(service.update(id, dto));
    }
}
