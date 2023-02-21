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
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin
@RestController
@RequestMapping("/lpr")
public class WebSocketController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hi")
     public ResponseEntity<String> hi(){
        return ResponseEntity.ok("hi");
    }
    

    //lpr傳入資料的API
    @PostMapping("/event")
    public String addOrUpdate(@RequestBody Users user){
        Users users = null;
        try{
            users = userService.addOrUpdateUser(user);
        }
        catch(Exception ex){
            ex.getMessage();
        }

        /* 透過 ws 傳更新資料給前端 , 此處傳一個 "update" 的text */
    for (WebSocketSession session : SocketTextHandler.getSessionList()) {
        try {
          session.sendMessage(
              new TextMessage("update"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
        return "success";
     }


      /*當websocket丟給前端後,前端call 這個API更新前端資料 */
      @GetMapping("/cams/latest")
      public Map<String, Users> getAllLatestRecordByCam() {
        Map<String, Users> latestCamsMap = new HashMap<>();
    
        latestCamsMap.put("cam1", latestCamsTool("cam1"));
        latestCamsMap.put("cam2", latestCamsTool("cam2"));

        return latestCamsMap;
      }
    
      public Users latestCamsTool(String cameraId) {
        Optional<Users> opr = userRepository.findAllLatestRecordByCameraId(cameraId);
    
        if (opr.isPresent()) {
          return opr.get();
        } else {
          return null;
        }
      }

}
