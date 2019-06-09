package com.halseyzsmith.sfgpetclinic.controllers;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import com.halseyzsmith.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    private final String OWNERS_VIEW = "owners/index";

    private final String OWNERS_ATTRIBUTE = "owners";

    private Set<Owner> owners;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).lastName("Smith").build());
        owners.add(Owner.builder().id(2L).lastName("Jones").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void listOwnersTest() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name(OWNERS_VIEW))
                .andExpect(model().attribute(OWNERS_ATTRIBUTE, hasSize(2)));

        verify(ownerService).findAll();
    }

    @Test
    void listOwnersByIndexTest() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name(OWNERS_VIEW))
                .andExpect(model().attribute(OWNERS_ATTRIBUTE, hasSize(2)));

        verify(ownerService).findAll();
    }

    @Test
    void findOwnersTest() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("notimplemented"));

        verifyZeroInteractions(ownerService);
    }
}