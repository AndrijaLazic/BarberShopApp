package com.BarberShop.UserApp.Controllers;

import com.BarberShop.UserApp.DTO.AccountsMsgDto;
import com.BarberShop.UserApp.FeignClients.AdminMSClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    AdminMSClient adminMSClient;
    private final StreamBridge streamBridge;
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @PostMapping
    public String message(@RequestBody String message) {
        adminMSClient.sendMessage(message);
        return "Poslata poruka poruka:" + message;
    }

    @GetMapping("/Test")
    public ResponseEntity<String> test(Boolean state) {
        ResponseEntity<String> response;
        try{
            response = adminMSClient.test(state);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.toString());
        }
        return response;
    }

    @GetMapping("/Test/IsAuthorized")
    public ResponseEntity<String> testAuthorized() {
        ResponseEntity<String> response;
        try{
            response = adminMSClient.testAuthorized();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }

    @PostMapping("/Test/TestRabbitMQ")
    public String sendMessage(@RequestBody String message) {
        var accountsMsgDto = new AccountsMsgDto(1L, "Korisnik", "EmailKorisnika", "1234");
        var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
        return "Message sent successfully";
    }
}
