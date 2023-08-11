package com.calculatrice.server.services;

import com.calculatrice.server.domain.equation.Equation;
import com.calculatrice.server.domain.equation.EquationRepository;
import com.calculatrice.server.domain.equation.OperationType;
import com.calculatrice.server.domain.rpn.RpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class RpnServiceImpl implements RpnService {
    @Autowired private EquationRepository repository;
    private List<String> parameter = Arrays.asList(
            "0","1","2","3","4","5","6","7","8","9","."
    );
    private List<String> operation = Arrays.asList(
            "+","-","*","/","%"
    );
    private String newStack = "Enter";
    private String clearEquation = "Clear";
    private String saveEquation = "Save";
    private String newEquation = "New";

    public RpnServiceImpl(EquationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Equation compute(String uid, String key) {
        try {
            if (newEquation.equals(key)) {
                Equation eq = new Equation(uid, "");
                repository.save(eq);
                return eq;
            }
            Equation eq = repository.getFor(uid);
            if (eq != null) {
                if (parameter.contains(key)) {
                    repository.save(eq.add(key));
                } else {
                    if (clearEquation.equals(key)) {
                        repository.save(eq.clear());
                        return eq;
                    }
                    if (saveEquation.equals(key)) {
                        repository.save(eq);
                        return eq;
                    }
                    if (newStack.equals(key)) {
                        repository.save(eq.add("|"));
                    } else if (operation.contains(key)) {
                        List<String> subEquation = Arrays.asList(eq.getEquation().split("\\|"));
                        if (subEquation.size() < 2) {
                            return eq;
                        }

                        List<String> subEquationToKeep = subEquation.subList(0, subEquation.size() - 2);

                        Double rightPar = Double.parseDouble(subEquation.get(subEquation.size() - 1));
                        Double leftPar = Double.parseDouble(subEquation.get(subEquation.size() - 2));
                        OperationType type = null;

                        switch (key) {
                            case "+":
                                type = OperationType.PLUS;
                                break;
                            case "-":
                                type = OperationType.MINUS;
                                break;
                            case "*":
                                type = OperationType.MULTIPLY;
                                break;
                            case "/":
                                type = OperationType.DIVIDE;
                                break;
                            case "%":
                                type = OperationType.MOD;
                                break;
                        }

                        if (type != null) {
                            String result = eq.compute(leftPar, rightPar, type);
                            List<String> newEq = new ArrayList<>(subEquationToKeep);
                            newEq.add(result);
                            eq.setEquation(String.join("|", newEq));
                            repository.save(eq);
                        }
                    }
                }
            } else {
                repository.save(new Equation(uid, key));
            }
            return eq;
        } catch (Exception e) {
            e = e;
        }
        return null;
    }

    @Override
    public Collection<Equation> getEquations() {
        return repository.getAll();
    }

    @Override
    public Equation getEquation(String uid) {
        return repository.getFor(uid);
    }

    @Override
    public void saveEquation(Equation eq) {
        repository.save(eq);
    }
}
