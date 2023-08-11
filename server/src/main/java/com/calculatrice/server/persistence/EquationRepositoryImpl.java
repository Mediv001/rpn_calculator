package com.calculatrice.server.persistence;

import com.calculatrice.server.domain.equation.Equation;
import com.calculatrice.server.domain.equation.EquationRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EquationRepositoryImpl implements EquationRepository {
    ConcurrentHashMap<String, Equation> data = new ConcurrentHashMap<>();
    {
        data.put("1", new Equation("1", "3|10|5"));
    }

    @Override
    public void save(Equation eq) {
        data.put(eq.getUid(), eq);
    }

    @Override
    public Equation getFor(String uid) {
        return data.get(uid);
    }

    @Override
    public Collection<Equation> getAll() {
        return data.values();
    }
}
