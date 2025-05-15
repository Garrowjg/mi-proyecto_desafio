package com.bibli.bia.repository;

import com.bibli.bia.Model.MultaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MultaRepository extends MongoRepository<MultaModel, String> {

    List<MultaModel> findByIdUsuario(String idUsuario);

    // Busca multas por nombre de usuario, con coincidencia parcial (ignore case)
    List<MultaModel> findByNombreUsuarioContainingIgnoreCase(String nombreUsuario);

    // Busca multas por estado de pagada o no
    List<MultaModel> findByPagada(boolean pagada);
}