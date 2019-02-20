package com.ecommerce.cursos.web;

import com.ecommerce.cursos.repositorio.CursoRepositorio;
import com.ecommerce.cursos.dominio.Curso;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private final CursoRepositorio cursoRepositorio;

    CursoController(CursoRepositorio cursoRepositorio){
        this.cursoRepositorio = cursoRepositorio;
    }

    @PostMapping()
    ResponseEntity<?> post(@Valid @RequestBody Curso curso, BindingResult result){

        if(result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());

        cursoRepositorio.saveAndFlush(curso);

        return ResponseEntity.ok().body(curso.getId());
    }

    @GetMapping()
    List<Curso> get(){
        return cursoRepositorio.findAll();
    }
}
