package com.BarberShop.AdminMicroservice.Controller;

import com.BarberShop.AdminMicroservice.DTOS.AccountsMsgDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Messages")
@RequiredArgsConstructor
public class MessagesController {
    private final StreamBridge streamBridge;
    private static final Logger log = LoggerFactory.getLogger(MessagesController.class);

    @PostMapping("/TestKafka")
    public String sendMessage(@RequestBody String message) {
        var accountsMsgDto = new AccountsMsgDto(1L, "Korisnik", "EmailKorisnika", "1234");
        var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
        return "Message sent successfully";
    }
}
