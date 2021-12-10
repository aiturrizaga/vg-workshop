package com.foodfix.management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "orderTakeClient", url = "${cluster.order-take-api}/orders")
public interface OrderTakeClient {

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id) throws Exception;
}
