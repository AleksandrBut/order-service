package com.microservices.order.stub;

import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import com.github.tomakehurst.wiremock.http.MimeType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubIsInStock(String skuCode, Integer quantity) {
        stubFor(get(urlPathEqualTo("/api/inventory/stock"))
                .withQueryParam("skuCode", equalTo(skuCode))
                .withQueryParam("quantity", equalTo(String.valueOf(quantity)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(ContentTypeHeader.KEY, MimeType.JSON.toString())
                        .withBody(Boolean.TRUE.toString())
                )
        );
    }
}
