package com.votacao.votacao_api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.votacao.votacao_api.dto.AgendaDTO;
import com.votacao.votacao_api.dto.AgendaResponseDTO;
import com.votacao.votacao_api.dto.VotingSessionDTO;
import com.votacao.votacao_api.model.Agenda;
import com.votacao.votacao_api.repository.AgendaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendaService {
	private final AgendaRepository agendaRepository;

    public Agenda createAgenda(AgendaDTO dto) {
        Agenda agenda = new Agenda();
        agenda.setTitle(dto.getTitle());
        return agendaRepository.save(agenda);
    }

    public Agenda getAgendaById(Long id) {
        return agendaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Agenda not found with ID: " + id));
    }

    public List<Agenda> getAllAgendas() {
        return agendaRepository.findAll();
    }
    
    public AgendaResponseDTO convertToDTO(Agenda agenda) {
        VotingSessionDTO sessionDTO = null;

        if (agenda.getVotingSession() != null) {
            sessionDTO = new VotingSessionDTO(
                agenda.getVotingSession().getId(),
                agenda.getVotingSession().getStartTime(),
                agenda.getVotingSession().getEndTime()
            );
        }

        return new AgendaResponseDTO(
            agenda.getId(),
            agenda.getTitle(),
            sessionDTO
        );
    }
}
