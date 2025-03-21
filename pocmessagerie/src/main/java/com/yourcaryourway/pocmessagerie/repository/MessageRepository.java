package com.yourcaryourway.pocmessagerie.repository;

import com.yourcaryourway.pocmessagerie.model.Message;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message, UUID> {
    @Query("SELECT * FROM messages " +
            "WHERE (id_expediteur = :idExpediteur AND id_destinataire = :idDestinataire) " +
            "OR (id_expediteur = :idDestinataire AND id_destinataire = :idExpediteur) " +
            "ORDER BY cree_le ASC")
    List<Message> findByIdExpediteurAndIdDestinataireOrIdExpediteurAndIdDestinataireOrderByCreeLeAsc(
            UUID idExpediteur, UUID idDestinataire);

    @Override
    <S extends Message> S save(S entity);
}
