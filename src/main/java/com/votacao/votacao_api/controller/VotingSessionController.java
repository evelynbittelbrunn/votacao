package com.votacao.votacao_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.votacao_api.dto.VotingSessionDTO;
import com.votacao.votacao_api.model.VotingSession;
import com.votacao.votacao_api.service.VotingSessionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class VotingSessionController {

	private final VotingSessionService votingSessionService;

    @PostMapping
    @Operation(summary = "Criação de sessão.", description = "Cria uma nova sessão de votação para uma pauta.")
    public ResponseEntity<VotingSession> openSession(@RequestBody VotingSessionDTO dto) {
        VotingSession session = votingSessionService.openSession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }
	
}
