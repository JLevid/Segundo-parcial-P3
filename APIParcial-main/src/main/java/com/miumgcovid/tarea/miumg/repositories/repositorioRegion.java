package com.miumgcovid.tarea.miumg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miumgcovid.tarea.miumg.models.RegionAPI;

@Repository
public interface repositorioRegion extends JpaRepository<RegionAPI, String> {

}
