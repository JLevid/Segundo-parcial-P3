package com.miumgcovid.tarea.miumg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.miumgcovid.tarea.miumg.repositories.RepositorioHiloProceso;

import jakarta.annotation.PostConstruct;

@Controller
public class MainController {
    @Autowired
    RepositorioHiloProceso proceso;

    @PostConstruct
    public void iniciarHilo(){
        proceso.iniciarHilo();
    }

}
