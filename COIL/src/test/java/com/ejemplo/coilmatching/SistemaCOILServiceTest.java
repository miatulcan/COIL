package com.ejemplo.coilmatching;

import com.ejemplo.coilmatching.model.Curso;
import com.ejemplo.coilmatching.repository.CursoRepository;
import com.ejemplo.coilmatching.service.SistemaCOILService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SistemaCOILServiceTest {

    private CursoRepository cursoRepository;
    private SistemaCOILService sistemaCOILService;

    @BeforeEach
    void setUp() {
        cursoRepository = Mockito.mock(CursoRepository.class);
        sistemaCOILService = new SistemaCOILService();
        sistemaCOILService = spy(sistemaCOILService);
        sistemaCOILService = new SistemaCOILService() {{
            cursoRepository = SistemaCOILServiceTest.this.cursoRepository;
        }};
    }

    @Test
    void testRealizarMatching() {
        Curso referencia = new Curso();
        referencia.setIdioma("Inglés");
        referencia.setTematica("IA");

        Curso match1 = new Curso();
        match1.setCodigo("C1");
        match1.setIdioma("Inglés");
        match1.setTematica("IA");

        Curso match2 = new Curso();
        match2.setCodigo("C2");
        match2.setIdioma("Inglés");
        match2.setTematica("IA");

        when(cursoRepository.findByIdiomaAndTematica("Inglés", "IA"))
                .thenReturn(Arrays.asList(match1, match2));

        List<Curso> result = sistemaCOILService.realizarMatching(referencia);

        assertEquals(2, result.size());
        verify(cursoRepository, times(1)).findByIdiomaAndTematica("Inglés", "IA");
    }
}