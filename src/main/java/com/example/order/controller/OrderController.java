package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import com.example.order.service.impl.OrderServiceImplementation;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrderList() throws IOException {
        return  orderService.getOrderList();
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable("orderId") String orderId) throws IOException {
        return  orderService.getOrder(orderId);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") String orderId){
        try {
            orderService.deleteOrder(orderId);
        } catch (CsvRequiredFieldEmptyException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CsvDataTypeMismatchException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> updateOrder(@PathVariable("orderId") String orderId, @RequestBody Order order){
        try {
            orderService.updateOrder(orderId,order);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (CsvRequiredFieldEmptyException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CsvDataTypeMismatchException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
