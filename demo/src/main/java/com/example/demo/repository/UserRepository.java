package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.*;
@Repository
public interface UserRepository extends CrudRepository<Users,Integer>{

    @Query(value = "SELECT * FROM lprtable WHERE cameraId=?1 AND NOT pass_status='查無此車號' ORDER by id DESC LIMIT 1", nativeQuery = true)
    Optional<Users> findAllLatestRecordByCameraId(String cameraId);
}
