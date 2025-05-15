package com.bibli.bia.service;

import com.bibli.bia.Model.LibroFisicoModel;
import com.bibli.bia.repository.LibroFisicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroFisicoService {

    @Autowired
    private LibroFisicoRepository libroFisicoRepository;

    // Guardar un libro físico
    public LibroFisicoModel guardarLibroFisico(LibroFisicoModel libroFisico) {
        return libroFisicoRepository.save(libroFisico);
    }

    // Obtener todos los libros físicos
    public List<LibroFisicoModel> obtenerTodosLosLibrosFisicos() {
        return libroFisicoRepository.findAll();
    }

    // Obtener libros físicos por categoría
    public List<LibroFisicoModel> obtenerLibrosFisicosPorCategoria(String categoria) {
        return libroFisicoRepository.findByCategoria(categoria);
    }

    // Realizar una reserva (actualizar el stock y reservado)
    public boolean reservarLibroFisico(String id) {
        Optional<LibroFisicoModel> libroOptional = libroFisicoRepository.findById(id);

        if (libroOptional.isPresent()) {
            LibroFisicoModel libro = libroOptional.get();

            if (libro.getStock() > 0) {
                libro.setStock(libro.getStock() - 1); // Disminuir el stock en 1
                libro.setReservado(libro.getReservado() + 1); // Aumentar el número de reservados
                libroFisicoRepository.save(libro); // Guardar cambios
                return true;
            } else {
                return false; // No hay stock disponible
            }
        }
        return false; // Libro no encontrado
    }

    // Cancelar una reserva (opcional: aumentar el stock y disminuir reservado)
    public boolean cancelarReservaLibroFisico(String id) {
        Optional<LibroFisicoModel> libroOptional = libroFisicoRepository.findById(id);

        if (libroOptional.isPresent()) {
            LibroFisicoModel libro = libroOptional.get();

            if (libro.getReservado() > 0) {
                libro.setStock(libro.getStock() + 1); // Aumentar el stock en 1
                libro.setReservado(libro.getReservado() - 1); // Disminuir los reservados
                libroFisicoRepository.save(libro);
                return true;
            } else {
                return false; // No hay reservas para cancelar
            }
        }
        return false; // Libro no encontrado
    }

    // Eliminar un libro físico
    public void eliminarLibroFisico(String id) {
        libroFisicoRepository.deleteById(id);
    }
}
