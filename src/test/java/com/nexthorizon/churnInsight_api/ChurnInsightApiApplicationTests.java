package com.nexthorizon.churnInsight_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles; // <--- Importante

@SpringBootTest
@ActiveProfiles("test") // <--- ESTA É A CHAVE MESTRA
class ChurnInsightApiApplicationTests {

  @Test
  void contextLoads() {}
}
