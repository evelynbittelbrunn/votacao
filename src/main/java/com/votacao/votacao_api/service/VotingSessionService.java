package com.votacao.votacao_api.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.votacao.votacao_api.dto.VotingSessionDTO;
import com.votacao.votacao_api.model.Agenda;
import com.votacao.votacao_api.model.VotingSession;
import com.votacao.votacao_api.repository.AgendaRepository;
import com.votacao.votacao_api.repository.VotingSessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotingSessionService {
	private final VotingSessionRepository votingSessionRepository;
    private final AgendaRepository agendaRepository;

    public VotingSession openSession(VotingSessionDTO dto) {
        Agenda agenda = agendaRepository.findById(dto.getAgendaId())
                .orElseThrow(() -> new NoSuchElementException("Agenda not found"));

        LocalDateTime start = Optional.ofNullable(dto.getStartTime()).orElse(LocalDateTime.now());
        LocalDateTime end = Optional.ofNullable(dto.getEndTime()).orElse(start.plusMinutes(1));

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }

        VotingSession session = new VotingSession();
        session.setAgenda(agenda);
        session.setStartTime(start);
        session.setEndTime(end);

        return votingSessionRepository.save(session);
    }

    public VotingSession getActiveSessionByAgendaId(Long agendaId) {
        LocalDateTime now = LocalDateTime.now();

        return votingSessionRepository.findByAgendaId(agendaId)
                .filter(s -> !now.isBefore(s.getStartTime()) && now.isBefore(s.getEndTime()))
                .orElseThrow(() -> new IllegalStateException("No active voting session for agenda"));
    }
}
