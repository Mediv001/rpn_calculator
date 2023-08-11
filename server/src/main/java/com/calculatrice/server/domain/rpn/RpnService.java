package com.calculatrice.server.domain.rpn;

import com.calculatrice.server.domain.equation.Equation;

import java.util.Collection;

public interface RpnService {
    Equation compute(String uid, String key);

    Collection<Equation> getEquations();

    Equation getEquation(String uid);

    void saveEquation(Equation eq);
}
