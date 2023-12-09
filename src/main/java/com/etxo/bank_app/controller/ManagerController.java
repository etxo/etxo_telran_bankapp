package com.etxo.bank_app.controller;


import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    @PostMapping
    public ResponseEntity<ManagerDto> create(@RequestBody ManagerDto manager){
        return ResponseEntity.ok(service.create(manager));
    }

    @GetMapping
    public ResponseEntity<Set<ManagerDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getManagerById(@PathVariable Long id){

        return ResponseEntity.ok(service.getManagerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerDto> update(@PathVariable Long id, @RequestBody ManagerDto dto){

        return ResponseEntity.ok(service.update(id, dto));
    }
}
