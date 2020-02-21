package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest implements ControllerTests {

    private VetController controller;

    @BeforeEach
    void setUp() {
        VetService service = new VetMapService(new SpecialityMapService());
        service.save(new Vet(1L, "Henry", "Gidson", null));
        service.save(new Vet(2L, "Genry", "Midson", null));

        controller = new VetController(service);
    }

    @DisplayName("Test proper view name and model data filling")
    @Test
    void listVets() {
        ModelMapImpl model = new ModelMapImpl();
        String view = controller.listVets(model);

        assertThat(view).isEqualTo("vets/index");
        Set<Vet> actualVets = (Set<Vet>) model.getData().get("vets");
        assertThat(actualVets).hasSize(2);
        assertThat(actualVets).extracting("id").containsExactlyInAnyOrder(1L, 2L);
    }

    static class ModelMapImpl implements Model {
        private Map<String, Object> data = new HashMap<>();

        @Override
        public void addAttribute(String key, Object o) {
            data.put(key, o);
        }

        @Override
        public void addAttribute(Object o) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Map<String, Object> getData() {
            return data;
        }
    }
}