package com.votacao.votacao_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.votacao_api.model.Associate;
import com.votacao.votacao_api.repository.AssociateRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/associates")
@RequiredArgsConstructor
public class AssociateController {
	private final AssociateRepository associateRepository;

    @PostMapping
    @Operation(summary = "Criação de associado.", description = "Cria um novo associado.")
    public ResponseEntity<Associate> createAssociate(@RequestBody Associate associate) {
        Associate saved = associateRepository.save(associate);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    @Operation(summary = "Listagem de associados.", description = "Lista todos os associados que foram cadastrados.")
    public List<Associate> getAllAssociates() {
        return associateRepository.findAll();
    }
}
