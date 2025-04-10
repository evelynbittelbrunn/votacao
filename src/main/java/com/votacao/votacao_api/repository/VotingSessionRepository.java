package com.votacao.votacao_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votacao.votacao_api.model.VotingSession;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
	Optional<VotingSession> findByAgendaId(Long agendaId);
}
