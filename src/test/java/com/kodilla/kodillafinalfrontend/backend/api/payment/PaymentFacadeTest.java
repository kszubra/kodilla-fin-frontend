package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
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
        //When
        PaymentDto testPayment = paymentFacade.getPayment(5L);

        //Then
        assertNotNull(testPayment);
        System.out.println(testPayment);
    }

    @Test
    public void testGetAllPayments() {
        //When
        List<PaymentDto> testPayments = paymentFacade.getPayments();

        //Then
        assertNotNull(testPayments);
        testPayments.forEach(System.out::println);
    }

    @Test
    public void testGetPaymentsByDateWithWrongFormat() {
        //When
        List<PaymentDto> testPayments = paymentFacade.getPaymentsByDate("sadas");

        //Then
        assertNotNull(testPayments);
        testPayments.forEach(System.out::println);
    }

    @Test
    public void testGetPaymentsByDate() {
        //When
        List<PaymentDto> testPayments = paymentFacade.getPaymentsByDate("2019-08-15");

        //Then
        assertNotNull(testPayments);
        testPayments.forEach(System.out::println);
    }

    @Test
    public void testAddPayment() {
        //Given
        PaymentDto testPayment = PaymentDto.builder()
                .status(PaymentStatus.PAID)
                .paymentDate(LocalDate.now().toString())
                .value(BigDecimal.valueOf(666.66))
                .build();
        Integer response = paymentFacade.addPayment(testPayment);

        //Then
        assertEquals(Integer.valueOf(200), response);

    }

    @Test
    public void testUpdatePayment() {
        //Given
        PaymentDto testPayment = PaymentDto.builder()
                .id(19L)
                .status(PaymentStatus.AWAITING)
                .paymentDate(LocalDate.now().toString())
                .value(BigDecimal.valueOf(99.66))
                .build();
        PaymentDto response = paymentFacade.updatePayment(testPayment);

        //Then
        assertEquals(testPayment, response);

    }

}