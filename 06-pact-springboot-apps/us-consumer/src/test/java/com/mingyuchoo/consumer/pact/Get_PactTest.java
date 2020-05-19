package com.mingyuchoo.consumer.pact;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Get_PactTest {

    public static final String COSNT_CONSUMER = "us_consumer";
    public static final String CONST_PROVIDER = "global_provider";
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
    @Pact(consumer = COSNT_CONSUMER)
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        return builder.given("GET /user")
                .uponReceiving("with no parameters")
                .path("/user")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(
                        new PactDslJsonBody()
                                .stringType("id")
                                .stringType("username")
                                // .stringType("userId")
                                .asBody())
                .toPact();
    }

    /** API를 제공하는 서비스 Provider가 계약을 따르는지 테스트할 코드 작성 */
    @Test
    @PactVerification()
    public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {
        // when
        ResponseEntity<String> response =
                new RestTemplate().getForEntity(mockProvider.getUrl() + "/user", String.class);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
        assertThat(response.getBody()).contains("id");
        assertThat(response.getBody()).contains("username");
        // assertThat(response.getBody()).contains("userId");
    }
}
