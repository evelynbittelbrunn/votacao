package com.votacao.votacao_api.dto;

import lombok.Data;

@Data
public class VoteDTO {
	
	private Long agendaId;
	
    private String associateId;
    
    private String vote;
    
}
