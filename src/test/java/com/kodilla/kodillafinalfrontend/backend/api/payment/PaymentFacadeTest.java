package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentFacadeTest {
    @Autowired
    private PaymentFacade paymentFacade;

    @Test
    public void testGetPaymentById() {
        //Given
        Payment testPayment = paymentFacade.getPayment(13L);

        //Then
        assertNotNull(testPayment);
        System.out.println(testPayment);
    }

    @Test
    public void testGetAllPayments() {
        //Given
        List<Payment> testPayments = paymentFacade.getPayments();

        //Then
        assertNotNull(testPayments);
        testPayments.forEach(System.out::println);
    }

}