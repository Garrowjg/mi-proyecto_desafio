package com.bibli.bia.service;

import com.bibli.bia.Model.MultaModel;
import com.bibli.bia.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    // Crear o guardar multa
    public MultaModel crearMulta(MultaModel multa) {
        return multaRepository.save(multa);
    }

    // Obtener multas por id de usuario (asumiendo que tienes este campo en el modelo)
    public List<MultaModel> obtenerMultasPorUsuario(String idUsuario) {
        return multaRepository.findByIdUsuario(idUsuario);
    }

    // Obtener multas por nombre de usuario (exacto o parcial, según repositorio)
    public List<MultaModel> obtenerMultasPorNombre(String nombreUsuario) {
        return multaRepository.findByNombreUsuarioContainingIgnoreCase(nombreUsuario);
    }

    // Obtener multas pendientes (no pagadas)
    public List<MultaModel> obtenerMultasPendientes() {
        return multaRepository.findByPagada(false);
    }

    // Marcar multa como pagada, agregando fecha de pago
    public MultaModel marcarComoPagada(String idMulta) {
        Optional<MultaModel> multaOpt = multaRepository.findById(idMulta);
        if (multaOpt.isPresent()) {
            MultaModel multa = multaOpt.get();
            if (!multa.isPagada()) {
                multa.setPagada(true);
                multa.setFechaPago(LocalDate.now());
                return multaRepository.save(multa);
            }
            // Si ya está pagada, devolverla sin cambios o null según lógica
            return multa;
        }
        return null;
    }

    // Eliminar multa por id
    public void eliminarMulta(String idMulta) {
        multaRepository.deleteById(idMulta);
    }

    // Obtener todas las multas
    public List<MultaModel> obtenerTodasMultas() {
        return multaRepository.findAll();
    }

    // Obtener multas ya pagadas
    public List<MultaModel> obtenerMultasPagadas() {
        return multaRepository.findByPagada(true);
    }
}
