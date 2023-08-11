package com.calculatrice.server.domain.equation;

import jdk.management.jfr.RecordingInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equation {
    private String uid;
    private String equation;

    public Equation add(String key) {
        equation += key;
        return this;
    }

    public Equation clear() {
        equation = "";
        return this;
    }

    public String compute(Double left, Double right, OperationType type) {
        switch (type) {
            case PLUS -> {
                return "" + (left + right);
            }
            case MINUS -> {
                return "" + (left - right);
            }
            case DIVIDE -> {
                return "" + (left / right);
            }
            case MULTIPLY -> {
                return "" + (left * right);
            }
            case MOD -> {
                return "" + (left % right);
            }
            default -> {
                return "";
            }
        }
    }
}
