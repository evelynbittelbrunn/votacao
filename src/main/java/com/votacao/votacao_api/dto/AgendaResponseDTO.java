package com.votacao.votacao_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaResponseDTO {
	
    private Long id;
    
    private String title;
    
    private VotingSessionDTO votingSession;
    
}
