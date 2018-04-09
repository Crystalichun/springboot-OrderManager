package com.example.order.service.impl;

import com.example.order.dao.OrderDao;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrderList() throws IOException {
        return orderDao.getOrderList();
    }

    @Override
    public Order getOrder(String orderId) throws FileNotFoundException {
        return orderDao.getOrder(orderId);
    }

    @Override
    public void deleteOrder(String orderId) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        orderDao.deleteOrder(orderId);
    }

    @Override
    public void updateOrder(String orderId, Order order) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        orderDao.updateOrder(orderId, order);
    }

}
