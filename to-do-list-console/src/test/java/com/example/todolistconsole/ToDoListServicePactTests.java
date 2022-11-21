package com.example.todolistconsole;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonArrayMinLike;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ToDoListService")
public class ToDoListServicePactTests {

    @Pact(consumer = "ToDoListConsoleApp", provider = "ToDoListService")
    RequestResponsePact getItemsPact(PactDslWithProvider builder) {
        return builder.given("items exist")
                .uponReceiving("get items")
                .method("GET")
                .path("/items")
                .willRespondWith()
                .status(200)
                .body(
                        newJsonArrayMinLike(2, array ->
                                array.object(object -> {
                                    object.numberType("id", 1);
                                    object.stringType("description", "clean house");
                                })
                        ).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getItemsPact", pactVersion = PactSpecVersion.V3)
    void getAllItems_whenItemsExist(MockServer server) {
        ToDoItem item = new ToDoItem(1, "clean house");
        List<ToDoItem> expected = Arrays.asList(item, item);

        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(server.getUrl()).build();
        List<ToDoItem> actual = new ToDoListService(restTemplate).getItems();

        assertEquals(expected, actual);
    }
}
