package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
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

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        // given
        Speciality speciality = new Speciality();

        // when
        service.delete(speciality);

        // then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        // given
        Speciality speciality = new Speciality();

        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality actualSpeciality = service.findById(1L);

        // then
        assertThat(actualSpeciality).isNotNull();
        then(specialtyRepository).should(timeout(100)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(timeout(100).times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(timeout(1000).atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @Test
    void delete() {
        // when
        service.delete(new Speciality());

        // then
        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrow() {
        willThrow(new RuntimeException("")).given(specialtyRepository).findById(anyLong());
        // given(specialtyRepository.findById(anyLong())).willThrow(new RuntimeException(""));

        assertThrows(RuntimeException.class, () -> service.findById(1L));
        then(specialtyRepository).should().findById(anyLong());
    }

    @Test
    void testSaveLambda() {
        // given
        String matchMe = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(matchMe);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        given(specialtyRepository.save(argThat(arg -> arg.getDescription().equals(matchMe)))).willReturn(savedSpecialty);

        Speciality returnedSpecialty = service.save(speciality);

        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }
}