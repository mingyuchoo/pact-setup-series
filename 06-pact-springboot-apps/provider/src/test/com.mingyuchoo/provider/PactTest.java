package com.mingyuchoo.provider;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8080")
@Provider("test_provider")
@PactBroker(host = "localhost", port = "9292", protocol = "http", authentication = @PactBrokerAuth(username = "admin", password = "admin"))
public class PactTest {

  @BeforeEach
  void setupTestTarget(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", 8080, "/"));
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State({ "provider accepts a new person" })
  public void toCreatePersonState() {
    User user = new User();
    user.setId(42L);
    user.setFirstName("Arthur");
    user.setLastName("Dent");
    when(userRepository.findById(eq(42L))).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);
  }

}
