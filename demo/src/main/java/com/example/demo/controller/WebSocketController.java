package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.SocketTextHandler;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/lpr")
public class WebSocketController {

    @Autowired
    private UserService userService;

    //lpr傳入資料的API
    @PostMapping("/event")
    public ResponseEntity<Users> addOrUpdate(@RequestBody Users user){
        Users users = null;
        try{
            users = userService.addOrUpdateUser(user);
        }
        catch(Exception ex){
            ex.getMessage();
        }

        /* 透過 ws 傳更新資料給前端 */
    for (WebSocketSession session : SocketTextHandler.getSessionList()) {
        try {
          session.sendMessage(
              new TextMessage("update"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
        return new ResponseEntity<Users>(users , HttpStatus.OK);
     }


      /*當websocket丟給前端後,前端call getAllCars這個API更新前端資料 */
      @GetMapping("/getAllCars")
      public ResponseEntity<List<Users>> getAllUsers(){
          List<Users> users = null;
          try{
              users = userService.getAllUsers();
          }
          catch(Exception ex){
              ex.getMessage();
          }
          return new ResponseEntity<List<Users>>(users , HttpStatus.OK);
      }

}
