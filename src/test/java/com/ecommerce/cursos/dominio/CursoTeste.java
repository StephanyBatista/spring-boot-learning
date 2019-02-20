package com.ecommerce.cursos.dominio;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CursoTeste {

    private Validator validator;

    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void deveCriarCurso(){
        final String nomeEsperado = "Teste";
        final double valorEsperado = 100d;

        Curso curso = new Curso(nomeEsperado, valorEsperado);

        assertEquals(nomeEsperado, curso.getNome());
        assertEquals(valorEsperado, curso.getValor(), 0);
    }

    @Test
    public void deveValidarValorDoCurso(){
        Curso curso = new Curso("Teste", 0);

        Set<ConstraintViolation<Curso>> violations = validator.validate(curso);
        assertNotNull(violations.stream().filter(v -> v.getMessage().equals("O valor do curso não pode ser menor que 100")).findAny().orElse(null));
    }

    @Test
    public void deveValidarNomeDoCurso(){
        Curso curso = new Curso(null, 100);

        Set<ConstraintViolation<Curso>> violations = validator.validate(curso);
        assertNotNull(violations.stream().filter(v -> v.getMessage().equals("Nome do curso é obrigatório")).findAny().orElse(null));
    }
}
