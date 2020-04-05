package com.mingyuchoo.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import java.util.HashMap;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PactPutTest {

    /** API를 제공하는 서비스 Provider를 Mocking */
    @Rule
    public PactProviderRuleMk2 mockProvider =
            new PactProviderRuleMk2("test_provider", "localhost", 8080, this);

    /** API를 사용하는 사례에 따른 계약(contract)을 구현 */
    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        return builder.given("test POST")
                .uponReceiving("POST REQUEST")
                .method("POST")
                .headers(headers)
                .body("{\"name\": \"Michael\"}")
                .path("/user")
                .willRespondWith()
                .status(201)
                .toPact();
    }

    /** API를 제공하는 서비스 Provider가 계약을 따르는지 테스트할 코드 작성 */
    @Test
    @PactVerification()
    public void givenGet_whenPostData_shouldReturn200WithProperHeaderAndBody() {

        // and
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = "{\"name\": \"Michael\"}";

        // when
        ResponseEntity<String> postResponse =
                new RestTemplate()
                        .exchange(
                                mockProvider.getUrl() + "/user",
                                HttpMethod.POST,
                                new HttpEntity<>(jsonBody, httpHeaders),
                                String.class);

        // then
        assertThat(postResponse.getStatusCode().value()).isEqualTo(201);
    }
}
