package guru.springframework.sfgpetclinic.mockitoexamples.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.SpecialitySDJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    private final static Long ID = 1L;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();

        service.delete(speciality);

        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        given(specialtyRepository.findById(ID)).willReturn(Optional.of(new Speciality()));

        Speciality actualSpeciality = service.findById(ID);

        then(specialtyRepository).should(timeout(100)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
        assertThat(actualSpeciality).isNotNull();
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        service.deleteById(ID);

        then(specialtyRepository).should(timeout(100).times(2)).deleteById(ID);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(ID);
        service.deleteById(ID);

        then(specialtyRepository).should(timeout(1000).atLeastOnce()).deleteById(ID);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(ID);
        service.deleteById(ID);

        then(specialtyRepository).should(atMost(5)).deleteById(ID);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(ID);
        service.deleteById(ID);

        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @Test
    void delete() {
        service.delete(new Speciality());

        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException()).when(specialtyRepository).delete(any(Speciality.class));

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));

        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void testFindByIdThrow() {
        given(specialtyRepository.findById(anyLong())).willThrow(new RuntimeException());
        // if method returns void
        // willThrow(new RuntimeException()).given(specialtyRepository).deleteById(anyLong());

        assertThrows(RuntimeException.class, () -> service.findById(1L));

        then(specialtyRepository).should().findById(anyLong());
    }

    @Test
    void testSaveLambda() {
        String matchMe = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(matchMe);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(ID);

        given(specialtyRepository.save(argThat(arg -> arg.getDescription().equals(matchMe)))).willReturn(savedSpecialty);

        Speciality returnedSpecialty = service.save(speciality);

        assertThat(returnedSpecialty.getId()).isEqualTo(ID);
    }
}