package com.votacao.votacao_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votacao.votacao_api.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
