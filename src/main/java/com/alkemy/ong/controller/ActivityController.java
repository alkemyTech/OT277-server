package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDTO> saveActivity(@Valid @RequestBody ActivityDTO activityDTO){
        return ResponseEntity.ok().body(activityService.saveActivity(activityDTO));
    }

    @PutMapping("/{id}")
    @PostMapping
    public ResponseEntity<ActivityDTO> updateActivity(@Valid @RequestBody ActivityDTO activityDTO,
                                                      @PathVariable String id){
        return ResponseEntity.ok().body(activityService.updateActivity(activityDTO, id));
    }
}
