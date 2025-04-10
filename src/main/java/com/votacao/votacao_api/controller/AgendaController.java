package com.votacao.votacao_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.votacao_api.dto.AgendaDTO;
import com.votacao.votacao_api.dto.AgendaResponseDTO;
import com.votacao.votacao_api.model.Agenda;
import com.votacao.votacao_api.service.AgendaService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/agendas")
@RequiredArgsConstructor
public class AgendaController {
	private final AgendaService agendaService;

    @PostMapping
    @Operation(summary = "Criação de pauta.", description = "Cria uma nova pauta para votação.")
    public ResponseEntity<Agenda> createAgenda(@RequestBody AgendaDTO dto) {
        Agenda agenda = agendaService.createAgenda(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
    }

    @GetMapping
    @Operation(summary = "Listagem de pautas.", description = "Lista todas as pautas que foram cadastradas.")
    public ResponseEntity<List<AgendaResponseDTO>> getAllAgendas() {
        List<Agenda> agendas = agendaService.getAllAgendas();
        List<AgendaResponseDTO> dtoList = agendas.stream()
                .map(agendaService::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta por pauta.", description = "Retorna os detalhes de uma pauta pelo seu id.")
    public ResponseEntity<AgendaResponseDTO> getAgendaById(@PathVariable("id") Long id) {
        Agenda agenda = agendaService.getAgendaById(id);
        AgendaResponseDTO dto = agendaService.convertToDTO(agenda);
        return ResponseEntity.ok(dto);
    }
}
