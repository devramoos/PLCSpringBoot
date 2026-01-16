package com.cblcontabil.cblcontabil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boasVindas")
public class boasVindas {

    @GetMapping
    public String boasVindas(){
        return "Boa vindas";
    }
}
