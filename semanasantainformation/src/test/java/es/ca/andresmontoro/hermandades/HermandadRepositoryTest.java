package es.ca.andresmontoro.hermandades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class HermandadRepositoryTest {

    @Autowired
    private HermandadRepository underTest;
    
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testFindById() {
        // given
        Long id = 1L;
        Hermandad hermandad = new Hermandad(
            "La Borriquita",
            "Cofradía de Nazarenos de Cristo Rey",
            "Lorem Ipsum",
            450,
            1500,
            LocalDate.of(1947, 1, 1),
            WeekDay.DOMINGO_DE_RAMOS
        );
        underTest.save(hermandad);

        // when
        Hermandad result = underTest.findById(id).orElse(null);

        //then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("La Borriquita", result.getApodo());
    }

    @Test
    void testDeleteById() {
        // given
        Long id = 1L;
        Hermandad hermandad = new Hermandad(
            "La Borriquita",
            "Cofradía de Nazarenos de Cristo Rey",
            "Lorem Ipsum",
            450,
            1500,
            LocalDate.of(1947, 1, 1),
            WeekDay.DOMINGO_DE_RAMOS
        );
        underTest.save(hermandad);

        // when
        underTest.deleteById(id);

        //then
        assertNull(underTest.findById(id).orElse(null));
    }
}
