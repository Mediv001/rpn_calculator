package com.calculatrice.server.domain.equation;

import java.util.Collection;

public interface EquationRepository {
    void save(Equation eq);
    Equation getFor(String uid);
    Collection<Equation> getAll();
}
