package com.micros.core;

import com.micros.core.controllers.PersonController;
import com.micros.core.enums.GenderEnum;
import com.micros.core.models.PersonModel;
import com.micros.core.services.PersonService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.personController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarPerson(){
        UUID uuid = UUID.randomUUID();

        Mockito.when(this.personService.findById(uuid)).thenReturn(Optional.of(new PersonModel("Fullname", GenderEnum.Female)));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/persons/{id}", uuid)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
