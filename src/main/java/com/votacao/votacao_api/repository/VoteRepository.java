package com.votacao.votacao_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votacao.votacao_api.model.Vote;
import com.votacao.votacao_api.model.enums.VoteChoice;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	boolean existsByAgendaIdAndAssociateId(Long agendaId, String string);
	
	long countByAgendaIdAndChoice(Long agendaId, VoteChoice choice);
    
    List<Vote> findAllByAgendaId(Long agendaId);
    
}
