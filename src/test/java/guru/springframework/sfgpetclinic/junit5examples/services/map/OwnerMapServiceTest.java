package guru.springframework.sfgpetclinic.junit5examples.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit @Nested tests example
 */
@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private PetTypeService petTypeService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        ownerMapService = new OwnerMapService(petTypeService, new PetMapService());
        System.out.println("outer class");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero() {
        assertThat(ownerMapService.findAll().size()).isZero();
    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero() {
        assertThat(ownerMapService.findAll().size()).isZero();
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);
            System.out.println("first nested");
        }

        @DisplayName("Test Pet Count")
        @Test
        void testPetCount() {
            assertThat(petTypeService.findAll().size()).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owners Tests - ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
                System.out.println("second nested");
            }

            @DisplayName("Save Owner")
            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");

                assertThat(ownerMapService.save(owner)).isNotNull();
            }

            @DisplayName("Save Owners Tests - ")
            @Nested
            class FindOwnersTests {

                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    assertThat(ownerMapService.findById(1L)).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {
                    assertThat(ownerMapService.findById(2L)).isNull();
                }
            }
        }
    }
}