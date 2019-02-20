package com.ecommerce.cursos.repositorio;

import com.ecommerce.cursos.dominio.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {}
