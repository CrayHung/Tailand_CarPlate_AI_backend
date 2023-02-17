package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.*;
@Repository
public interface UserRepository extends CrudRepository<Users,Integer>{
    
}
