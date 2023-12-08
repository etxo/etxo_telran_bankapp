package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.reposi.ManagerRepository;
import com.etxo.bank_app.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    @PostMapping
    public ResponseEntity<ManagerDto> create(@RequestBody ManagerDto manager){
        return ResponseEntity.ok(service.create(manager));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getManagerById(@PathVariable Long id){

        return ResponseEntity.ok(service.getManagerById(id));
    }
}
