package com.votacao.votacao_api.dto;

import lombok.Data;

@Data
public class ResultDTO {
	
	private Long agendaId;
	
    private String agendaTitle;
    
    private long totalVotes;
    
    private long yesVotes;
    
    private long noVotes;
    
    private String result;
    
}
