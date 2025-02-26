package base.example.demo.controller;

import base.example.demo.dto.OfferDto;
import base.example.demo.mapper.OfferMapper;
import base.example.demo.service.OfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OfferControllerTest {

    private MockMvc mvc; //объявляем но пока пустая

    @Mock //ктрл q докуменатция
    private OfferService offerService; //здаем заглушку тк она внутри касмотер контроллер

    @InjectMocks //внедряем заглушку моск в кконтроллер
    private OfferController offerController;

    private OfferMapper offerMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(offerController).build();  // создаем собираем мок мвс  на основе объекта offer
    }

    @Test
    void createOffer_shouldBeSuccess() throws Exception {
        OfferDto offerDto = new OfferDto();
        offerDto.setPercent(20L);
        offerDto.setSum(90000L);

        Mockito.when(offerService.createOffer(ArgumentMatchers.any(OfferDto.class)))
                .thenReturn(offerDto);

        mvc.perform(MockMvcRequestBuilders.post("/offer/create")
                        .content("{\"sum\": 90000, \"percent\": 20}") // JSON тело
                        .contentType(MediaType.APPLICATION_JSON)) // Укажите формат JSON
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.sum").value(90000L))
                .andExpect(jsonPath("$.percent").value(20L));


    }

    @Test
    void createOffer_shouldBeError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/offer/create")
                        .content("{\"wrong\": wrong, \"percent\": 20}") // JSON тело
                        .contentType(MediaType.APPLICATION_JSON)) // Укажите формат JSON
                .andExpect(status().is4xxClientError());
    }

}
