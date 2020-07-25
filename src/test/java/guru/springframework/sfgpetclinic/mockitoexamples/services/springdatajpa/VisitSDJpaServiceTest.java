package guru.springframework.sfgpetclinic.mockitoexamples.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.VisitSDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    private final static Long ID = 1L;

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService service;

    @Test
    void findAll() {
        given(visitRepository.findAll()).willReturn(Set.of(new Visit()));

        Set<Visit> actualVisits = service.findAll();

        then(visitRepository).should().findAll();
        assertThat(actualVisits).isNotEmpty();
    }

    @Test
    void findById() {
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(new Visit()));

        Visit actualVisit = service.findById(ID);

        then(visitRepository).should().findById(ID);
        assertThat(actualVisit).isNotNull();
    }

    @Test
    void save() {
        given(visitRepository.save(any(Visit.class))).willReturn(new Visit());

        Visit savedVisit = service.save(new Visit());

        then(visitRepository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        service.delete(new Visit());

        then(visitRepository).should().delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        then(visitRepository).should().deleteById(ID);
    }
}