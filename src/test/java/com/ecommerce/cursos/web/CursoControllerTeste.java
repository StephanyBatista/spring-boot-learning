package com.ecommerce.cursos.web;

import com.ecommerce.cursos.dominio.Curso;
import com.ecommerce.cursos.repositorio.CursoRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CursoControllerTeste {

    private CursoRepositorio repositorio;
    private CursoController controller;
    private BindingResult result;
    private ObjectError objectError;

    @Before
    public void setUp(){
        repositorio = mock(CursoRepositorio.class);
        controller = new CursoController(repositorio);
        result = mock(BindingResult.class);
        objectError = mock(ObjectError.class);
    }

    @Test
    public void deveSalvarCurso(){
        Curso curso = new Curso("Nome", 100);
        when(result.hasErrors()).thenReturn(false);

        ResponseEntity response = controller.post(curso, result);

        assertEquals((long)response.getBody(), curso.getId());
    }

    @Test
    public void naoDeveSalvarCursoEmCasoDeErro(){
        Curso curso = new Curso("Nome", 0);
        when(result.hasErrors()).thenReturn(true);
        when(result.getAllErrors()).thenReturn(Arrays.asList(objectError));

        ResponseEntity response = controller.post(curso, result);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
