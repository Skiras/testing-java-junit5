package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock(lenient = true)
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController controller;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();

                    String lastName = invocation.getArgument(0);

                    switch (lastName) {
                        case "%John%":
                            owners.add(new Owner(1L, "John", "John"));
                            return owners;
                        case "%DonNotFindMe%":
                            return owners;
                        case "%FindMe%":
                            owners.add(new Owner(1L, "John", "John"));
                            owners.add(new Owner(2L, "Joe", "Joe"));
                            return owners;
                    }

                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void processFindFormWildcardString() {
        String viewName = controller.processFindForm(new Owner(1L, "John", "John"), bindingResult, null);

        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%John%");
        assertThat(viewName).isEqualToIgnoringCase("redirect:/owners/1");
        verifyNoInteractions(model);
    }

    @Test
    void processFindFormWildcardStringNotFound() {
        String viewName = controller.processFindForm(new Owner(1L, "John", "DonNotFindMe"), bindingResult, null);

        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%DonNotFindMe%");
        assertThat(viewName).isEqualToIgnoringCase("owners/findOwners");
        verifyNoInteractions(model);
    }

    @Test
    void processFindFormWildcardStringFound() {
        InOrder inOrder = inOrder(ownerService, model);

        String viewName = controller.processFindForm(new Owner(1L, "John", "FindMe"), bindingResult, model);

        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%FindMe%");
        assertThat(viewName).isEqualToIgnoringCase("owners/ownersList");

        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void processCreationFormHasErrors() {
        given(bindingResult.hasErrors()).willReturn(true);

        String result = controller.processCreationForm(new Owner(1L, "John", "John"), bindingResult);

        assertEquals(OWNERS_CREATE_OR_UPDATE_OWNER_FORM, result);
        then(bindingResult).should().hasErrors();
        then(ownerService).shouldHaveNoInteractions();
    }

    @Test
    void processCreationFormNoErrors() {
        Owner savedOwner = new Owner(5L, "John", "John");
        given(ownerService.save(any())).willReturn(savedOwner);
        given(bindingResult.hasErrors()).willReturn(false);

        String result = controller.processCreationForm(savedOwner, bindingResult);

        assertEquals(REDIRECT_OWNERS_5, result);
        then(bindingResult).should().hasErrors();
        then(ownerService).should().save(any());
    }
}