package com.mingyuchoo.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
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

    public static final String COSNT_CONSUMER = "test_consumer";
    public static final String CONST_PROVIDER = "test_provider";
    public static final String CONST_PROVIDER_HOST = "localhost";
    public static final int CONST_PROVIDER_PORT = 8080;

    /** API를 제공하는 서비스 Provider를 Mocking */
    @Rule
    public PactProviderRuleMk2 mockProvider =
            new PactProviderRuleMk2(CONST_PROVIDER, CONST_PROVIDER_HOST, CONST_PROVIDER_PORT, this);

    /**
     * API를 사용하는 사례에 따른 계약(contract)을 구현 ref -
     * https://github.com/DiUS/pact-jvm/tree/master/consumer/pact-jvm-consumer-junit
     */
    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        PactDslJsonBody body = new PactDslJsonBody().stringType("name").asBody();

        return builder.given("test POST")
                .uponReceiving("POST REQUEST")
                .method("POST")
                .path("/user")
                .headers(headers)
                .body(body)
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
