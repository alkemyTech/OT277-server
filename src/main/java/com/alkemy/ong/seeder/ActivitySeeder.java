package com.alkemy.ong.seeder;

import com.alkemy.ong.entity.ActivityEntity;
import com.alkemy.ong.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ActivitySeeder implements CommandLineRunner {

    private final ActivityRepository activityRepository;


    @Override
    public void run(String... args) throws Exception {
        seedActivityTable();
    }
    private void seedActivityTable(){
        if(activityRepository.count() == 0){
            createActivity();
        }
    }

    private void createActivity(){
        ActivityEntity primary = new ActivityEntity();
        primary.setName("Apoyo Escolar para el nivel Primario");
        primary.setContent("El espacio de apoyo escolar es el corazón del área educativa Se realizan los " +
                "talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el" +
                "contraturno");
        primary.setImage("nivel_primario.png");

        ActivityEntity secundary = new ActivityEntity();
        secundary.setName("Apoyo Escolar Nivel Secundaria");
        secundary.setContent("Del mismo modo que en primaria, este taller es el corazón del área" +
                "secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a" +
                "18 horas en el contraturno");
        secundary.setImage("nivel_secundary.png");

        ActivityEntity tutories = new ActivityEntity();
        tutories.setName("Tutorías");
        tutories.setContent("Es un programa destinado a jóvenes a partir del tercer año de secundaria," +
                "cuyo objetivo es garantizar su permanencia en la escuela y construir un" +
                "proyecto de vida que da sentido al colegio");
        tutories.setImage("tutorias.png");
        var seeds = new ArrayList<ActivityEntity>();
        seeds.add(primary);
        seeds.add(secundary);
        seeds.add(tutories);
        activityRepository.saveAll(seeds);
    }
}
