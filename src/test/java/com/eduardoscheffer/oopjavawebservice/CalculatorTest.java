package com.eduardoscheffer.oopjavawebservice;

import com.eduardoscheffer.oopjavawebservice.controllers.utils.Calculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Mock
    private Calculator calculator = new Calculator();

    @BeforeAll
    static void print() {
        System.out.println("Testes iniciados...");
    }
    @AfterAll
    static void printFim() {
        System.out.println("Testes finalizados.");
    }
    @Test
    @DisplayName("Teste de adicao")
    void add() {

        assertEquals(2, calculator.add(1, 1), "Assegura que 1 mais um eh 2");

    }

    @Test
    @DisplayName("Teste da Exceção")
    void exceptionTesting() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add(-1, 3);
        });
        assertEquals("First argument cannot be negative", exception.getMessage());
    }
}