package com.etxo.bank_app.controller;


import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.service.ManagerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<ManagerDto> create(@RequestBody @Valid ManagerDto manager){
        return ResponseEntity.ok(service.create(manager));
    }

    @GetMapping
    @Secured({"ADMIN", "MANAGER"})
    public ResponseEntity<Set<ManagerDto>> getAll(){

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Secured({"ADMIN", "MANAGER"})
    //@PostAuthorize()
    public ResponseEntity<ManagerDto> getManagerById(@PathVariable Long id){

        return ResponseEntity.ok(service.getManagerById(id));
    }

    @PutMapping("/{id}")
    @Secured("MANAGER")
    public ResponseEntity<ManagerDto> update(@PathVariable Long id, @RequestBody ManagerDto dto){

        return ResponseEntity.ok(service.update(id, dto));
    }
}
