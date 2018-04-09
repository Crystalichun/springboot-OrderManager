package com.example.order.dao;

import com.example.order.model.Order;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface OrderDao {

    public List<Order> getOrderList() throws IOException;

    public Order getOrder(String orderId) throws FileNotFoundException;

    public void deleteOrder(String orderId) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;

    public void updateOrder(String orderId, Order order) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;


}
