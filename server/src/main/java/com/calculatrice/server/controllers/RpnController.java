package com.calculatrice.server.controllers;

import com.calculatrice.server.domain.equation.Equation;
import com.calculatrice.server.domain.rpn.RpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rpn")
public class RpnController {

    @Autowired
    private RpnService rpnService;

    @RequestMapping(value = "/equations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Equation>> getEquations() {
        return ResponseEntity.ok(rpnService.getEquations());
    }

    @CrossOrigin
    @PostMapping(value = "/equation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEquation(@RequestBody Equation eq) {
        rpnService.saveEquation(eq);
    }

    @CrossOrigin
    @PostMapping(value = "/equation/compute", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Equation> computeEquation(@RequestBody Equation eq) {
        return ResponseEntity.ok(rpnService.compute(eq.getUid(), eq.getEquation()));
    }
}
