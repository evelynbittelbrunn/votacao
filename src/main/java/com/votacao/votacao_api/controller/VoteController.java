package com.votacao.votacao_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.votacao_api.dto.ResultDTO;
import com.votacao.votacao_api.dto.VoteDTO;
import com.votacao.votacao_api.service.VoteService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

	private final VoteService voteService;

    @PostMapping
    @Operation(summary = "Registro de voto.", description = "Registra um voto de um associado em uma pauta.")
    public ResponseEntity<String> vote(@RequestBody VoteDTO dto) {
        voteService.castVote(dto);
        return ResponseEntity.ok("Vote registered successfully.");
    }

    @GetMapping("/result/{agendaId}")
    @Operation(summary = "Resultado da pauta.", description = "Retorna o resultado da votação de uma pauta.")
    public ResponseEntity<ResultDTO> getVotingResult(@PathVariable("agendaId") Long agendaId) {
        return ResponseEntity.ok(voteService.computeResult(agendaId));
    }
	
}
