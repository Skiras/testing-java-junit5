package guru.springframework.sfgpetclinic.mockitoexamples.services.springdatajpa;

import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.VetSDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    private final static Long ID = 1L;

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetSDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(ID);

        then(vetRepository).should().deleteById(ID);
    }
}