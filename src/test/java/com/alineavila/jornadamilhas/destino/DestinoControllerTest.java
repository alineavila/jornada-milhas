package com.alineavila.jornadamilhas.destino;

import com.alineavila.jornadamilhas.controller.DestinoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DestinoController.class)
public class DestinoControllerTest {

   private MockMvc mockMvc;

   @Test
   public void testeStatusOk() throws Exception {
       this.mockMvc.perform(get("/depoimentos"))
               .andExpect(MockMvcResultMatchers.status().isOk());
   }

}
