package com.miumgcovid.tarea.miumg.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miumgcovid.tarea.miumg.models.RegionAPI;
import com.miumgcovid.tarea.miumg.repositories.repositorioRegion;

@Service
public class ServicioPeticion{

    final String regionesURL = "https://covid-19-statistics.p.rapidapi.com/regions";
    final String provinciasURL = "https://covid-19-statistics.p.rapidapi.com/provinces";
    final String reporteURL = "https://covid-19-statistics.p.rapidapi.com/reports?iso=GTM&date=2022-04-16";
    final RestTemplate request = new RestTemplate();
    private HttpHeaders header = new HttpHeaders();
    @Autowired
    private repositorioRegion repositorioRegion;

    public ServicioPeticion(){

    }

    public void buscaRegiones(){

        //headers
        this.header.setContentType(MediaType.APPLICATION_JSON);
        this.header.set("X-RapidAPI-Key", "2505eda46amshc60713983b5e807p1da25ajsn36febcbf4a71");
        this.header.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

        //Creación de entidad con cabecera
        HttpEntity<Void> requestEntity = new HttpEntity<>(this.header);

        //Hacer la peticion
        ResponseEntity<String> response = this.request.exchange(this.regionesURL, HttpMethod.GET, requestEntity, String.class);

        //Convertir el body a un JSON
        ObjectMapper mapper = new ObjectMapper();
        try{
            
            Map<String, Object> responseMap = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});

            Object data = responseMap.get("data");
            List<Map<String, Object>> arrayData = (List<Map<String, Object>>) data;
            
            for(Map<String, Object> dato : arrayData){
                RegionAPI region = new RegionAPI();
                region.setIso((String) dato.get("iso"));
                region.setName((String) dato.get("name"));
                repositorioRegion.save(region);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void buscaProvincias(){

        //Arma la URL con parametros
        String URLFinal = this.provinciasURL + "?iso=GTM";

        //headers
        this.header = new HttpHeaders();
        this.header.setContentType(MediaType.APPLICATION_JSON);
        this.header.set("X-RapidAPI-Key", "2505eda46amshc60713983b5e807p1da25ajsn36febcbf4a71");
        this.header.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");
        
        //Cuerpo o data
        Map<String, Object> data = new HashMap<>();
        data.put("iso", "GTM");

        //Creacion de entidad con cabecera y data
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, this.header);

        //Hacemos la petición
        ResponseEntity<String> response = this.request.exchange(URLFinal, HttpMethod.GET, requestEntity, String.class);

        System.out.println(response.getBody());

    }

}
