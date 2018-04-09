package com.example.order.service;

import com.example.order.model.Order;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface OrderService {
    public List<Order> getOrderList() throws IOException;
    public Order getOrder(String orderId) throws FileNotFoundException;
    public void deleteOrder(String orderId) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;
    public void updateOrder(String orderId, Order order) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;

}
