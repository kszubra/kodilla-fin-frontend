package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        Payment testPayment = paymentFacade.getPayment(5L);

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

    @Test
    public void testGetPaymentsByDateWithWrongFormat() {
        //Given
        List<Payment> testPayments = paymentFacade.getPaymentsByDate("sadas");

        //Then
        assertNotNull(testPayments);
        testPayments.forEach(System.out::println);
    }

    @Test
    public void testGetPaymentsByDate() {
        //Given
        List<Payment> testPayments = paymentFacade.getPaymentsByDate("2019-08-15");

        //Then
        assertNotNull(testPayments);
        testPayments.forEach(System.out::println);
    }

    @Test
    public void testAddPayment() {
        //Given
        Payment testPayment =Payment.builder()
                .status(PaymentStatus.PAID)
                .paymentDate(LocalDate.now())
                .value(BigDecimal.valueOf(666.66))
                .build();
        Integer response = paymentFacade.addPayment(testPayment);

        //Then
        assertEquals(Integer.valueOf(200), response);

    }

    @Test
    public void testUpdatePayment() {
        //Given
        Payment testPayment =Payment.builder()
                .id(19L)
                .status(PaymentStatus.AWAITING)
                .paymentDate(LocalDate.now())
                .value(BigDecimal.valueOf(99.66))
                .build();
        Payment response = paymentFacade.updatePayment(testPayment);

        //Then
        assertEquals(testPayment, response);

    }

}