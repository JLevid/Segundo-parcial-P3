package com.miumgcovid.tarea.miumg.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.miumgcovid.tarea.miumg.services.ServicioPeticion;

@Repository
public class RepositorioHiloProceso implements Runnable{

    @Value("${api.delay.seconds}")
    private int delayHilo;
    @Autowired
    private ServicioPeticion servicio;

    public void iniciarHilo(){
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run(){
        try {
            Thread.sleep(delayHilo * 1000L);
            servicio.buscaProvincias();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
