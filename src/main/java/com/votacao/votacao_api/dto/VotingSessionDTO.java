package com.votacao.votacao_api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingSessionDTO {
	
	private Long agendaId;
	
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
}
