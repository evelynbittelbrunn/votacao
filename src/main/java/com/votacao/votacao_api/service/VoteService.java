package com.votacao.votacao_api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.votacao.votacao_api.dto.ResultDTO;
import com.votacao.votacao_api.dto.VoteDTO;
import com.votacao.votacao_api.model.Agenda;
import com.votacao.votacao_api.model.Associate;
import com.votacao.votacao_api.model.Vote;
import com.votacao.votacao_api.model.VotingSession;
import com.votacao.votacao_api.model.enums.VoteChoice;
import com.votacao.votacao_api.repository.AssociateRepository;
import com.votacao.votacao_api.repository.VoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteService {
	private final VoteRepository voteRepository;
    private final AssociateRepository associateRepository;
    private final AgendaService agendaService;
    private final VotingSessionService votingSessionService;

    public Vote castVote(VoteDTO dto) {
        VotingSession session = votingSessionService.getActiveSessionByAgendaId(dto.getAgendaId());
        Agenda agenda = session.getAgenda();

        Associate associate = associateRepository.findById(dto.getAssociateId())
                .orElseThrow(() -> new NoSuchElementException("Associate not found"));

        if (voteRepository.existsByAgendaIdAndAssociateId(agenda.getId(), associate.getId())) {
            throw new IllegalArgumentException("Associate has already voted in this agenda");
        }

        Vote vote = new Vote();
        vote.setAgenda(agenda);
        vote.setAssociate(associate);
        vote.setChoice(VoteChoice.valueOf(dto.getVote().toUpperCase()));

        return voteRepository.save(vote);
    }

    public ResultDTO computeResult(Long agendaId) {
        Agenda agenda = agendaService.getAgendaById(agendaId);
        List<Vote> votes = voteRepository.findAllByAgendaId(agendaId);

        long yesVotes = votes.stream().filter(v -> v.getChoice() == VoteChoice.YES).count();
        long noVotes = votes.stream().filter(v -> v.getChoice() == VoteChoice.NO).count();

        ResultDTO result = new ResultDTO();
        result.setAgendaId(agendaId);
        result.setAgendaTitle(agenda.getTitle());
        result.setYesVotes(yesVotes);
        result.setNoVotes(noVotes);
        result.setTotalVotes(votes.size());
        result.setResult(yesVotes > noVotes ? "APROVADA" : (noVotes > yesVotes ? "REPROVADA" : "EMPATE"));

        return result;
    }
}
