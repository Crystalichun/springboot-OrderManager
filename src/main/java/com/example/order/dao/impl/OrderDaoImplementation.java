package com.example.order.dao.impl;

import com.example.order.config.CSVConfig;
import com.example.order.dao.OrderDao;
import com.example.order.model.Order;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDaoImplementation implements OrderDao{
    @Autowired
    private CSVConfig csvConfig;

    @Override
    public List<Order> getOrderList() throws FileNotFoundException {
        return new CsvToBeanBuilder(new FileReader(csvConfig.getFilePath()))
                .withType(Order.class).build().parse();
    }

    @Override
    public Order getOrder(String orderId) throws FileNotFoundException {
        return  getOrderList().stream().filter(order -> {
            return order.getOrderId().equals(orderId);
        }).findFirst().get();
    }

    @Override
    public void deleteOrder(String orderId) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        updateCSVFile(getOrderList().stream().filter(order -> !order.getOrderId().equals(orderId)).collect(Collectors.toList()));

    }

    @Override
    public void updateOrder(String orderId, Order order) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<Order> updatedList = getOrderList().stream().map(o -> {
            if(o.getOrderId().equals(orderId)){
                if(order.getCustomerId()!= null){
                    o.setCustomerId(order.getCustomerId());
                }
                if(order.getCreateTime()!= null){
                    o.setCreateTime(order.getCreateTime());
                }
                if(order.getUnitPrice() >= 0){
                    o.setUnitPrice(order.getUnitPrice());
                }
                if(order.getQuantity() >= 0){
                    o.setQuantity(order.getQuantity());
                }
                if(order.getTotalPrice() >=0){
                    o.setTotalPrice(order.getTotalPrice());
                }
            }
            return o;
        }).collect(Collectors.toList());
        updateCSVFile(updatedList);
    }

    private void updateCSVFile(List<Order> list) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Writer writer = new FileWriter(csvConfig.getFilePath());
        StatefulBeanToCsvBuilder beanToCsvBuilder = new StatefulBeanToCsvBuilder(writer);
        StatefulBeanToCsv<Order> beanToCsv =  beanToCsvBuilder.build();
        beanToCsv.write(list);
        writer.close();
    }

}
