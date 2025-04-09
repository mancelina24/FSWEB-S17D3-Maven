package com.workintech.zoo.controller;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init()
    {
        koalas = new HashMap<>();
    }


    @GetMapping()
    public List<Koala> findAll()
    {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala findById(@PathVariable int id){
        if(id<=0){
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if(!koalas.containsKey(id)){
            throw new ZooException("Koala id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala)
    {
        if(koala == null)
        {
            throw new ZooException("Kangaroo instance is not null!!",HttpStatus.BAD_REQUEST);
        }
        if(koalas.containsKey(koala.getId())) {
            throw new ZooException("Koala id already exists: " + koala.getId(), HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable int id, @RequestBody Koala koala)
    {
        if(id <= 0)
        {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if(!koalas.containsKey(id))
        {
            throw new ZooException("Kangaroo id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        koalas.put(id,koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id)
    {
        if(id <= 0)
        {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if(!koalas.containsKey(id))
        {
            throw new ZooException("Koala id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        Koala koala = koalas.get(id);
        koalas.remove(id);
        return koala;
    }

    }
