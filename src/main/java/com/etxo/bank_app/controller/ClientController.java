package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ClientDtoUpdate;
import com.etxo.bank_app.security.service.UserService;
import com.etxo.bank_app.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final UserService userService;


    @Operation(summary = "gets all clients")
    @Secured({"ADMIN", "MANAGER"})
    @GetMapping
    public ResponseEntity<Set<ClientDto>> getClients(){

        return ResponseEntity.ok(service.getClients());
    }

    @Operation(summary = "gets client by id",
                description = "client should exist in the database")
    @ApiResponse(responseCode = "404",
                    description = "no client with this id")
    @GetMapping("/{id}")
    @Secured({"ADMIN", "MANAGER"})
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){

        return ResponseEntity.ok(service.getClientById(id));
    }

    @Operation(summary = "gets client by email",
                description = "client should exist in the database")
    @ApiResponse(responseCode = "404",
                    description = "no client with this email")
    @GetMapping("/by_email/{email}")
    //@PostAuthorize("returnObject.body.email == userService" +
    //      ".loadUserByUsername(principal.username).getEmail()")
    @Secured("MANAGER")
    //@PreAuthorize("this.isOwner(#email)")
    public ResponseEntity<ClientDto> getClientByEmail(@PathVariable String email){

        if(userService.isOwner(email)) {
            return ResponseEntity.ok(service.getClientByEmail(email));
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @Operation(summary = "creates client",
                description = "client may not exist in the database")
    @ApiResponse(responseCode = "404",
                    description = "email exists already")
    @PostMapping
    @Secured("MANAGER")
    public ResponseEntity<ClientDto> create(
                    @RequestBody @Valid ClientDto client){

        return ResponseEntity.ok(service.create(client));
    }

    @Operation(summary = "updates client by id",
                description = "client should exist in the database")
    @ApiResponse(responseCode = "404",
            description = "client not found")
    @PutMapping("/update/{id}")
    @Secured("MANAGER")
    public ResponseEntity<ClientDto> update(
            @PathVariable Long id, @RequestBody ClientDtoUpdate dto){

        return ResponseEntity.ok(service.updateById(id, dto));
    }

    @Operation(summary = "deactivates client by id",
                description = "client should exist in the database")
    @ApiResponse(responseCode = "404",
            description = "client not found")
    @DeleteMapping("/{id}")
    @Secured("MANAGER")
    public ResponseEntity<ClientDto> delete(@PathVariable Long id){

        return ResponseEntity.ok(service.deleteById(id));
    }
}
