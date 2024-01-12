package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ClientDtoUpdate;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.service.UserService;
import com.etxo.bank_app.service.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final UserService userService;

    @Secured({"ADMIN", "MANAGER"})
    @GetMapping
    public ResponseEntity<Set<ClientDto>> getClients(){

        return ResponseEntity.ok(service.getClients());
    }

    @GetMapping("/{id}")
    @Secured({"ADMIN", "MANAGER"})
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){

        return ResponseEntity.ok(service.getClientById(id));
    }

    @GetMapping("/by_email/{email}")
    //@PostAuthorize("returnObject.body.email == userService" +
    //      ".loadUserByUsername(principal.username).getEmail()")
    //@Secured("MANAGER")
    //@PreAuthorize("this.isOwner(#email)")
    public ResponseEntity<ClientDto> getClientByEmail(@PathVariable String email){
        if(userService.isOwner(email)) {
            return ResponseEntity.ok(service.getClientByEmail(email));
        }
        else return ResponseEntity.badRequest().body(null);
    }



    @PostMapping
    @Secured("MANAGER")
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto client){
        return ResponseEntity.ok(service.create(client));
    }

    @PutMapping("/update/{id}")
    @Secured("MANAGER")
    public ResponseEntity<ClientDto> update(@PathVariable Long id,
                                            @RequestBody ClientDtoUpdate dto){

        return ResponseEntity.ok(service.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    @Secured("MANAGER")
    public ResponseEntity<ClientDto> delete(@PathVariable Long id){

        return ResponseEntity.ok(service.delete(id));
    }
}
