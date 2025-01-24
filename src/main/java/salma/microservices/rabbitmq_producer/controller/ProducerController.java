package amine.microservices.rabbitmq_producer.controller;


import amine.microservices.rabbitmq_producer.domain.User;
import amine.microservices.rabbitmq_producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ProducerController {

    private final ProducerService producerService;
    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Value("${app.message}")
    private String response;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        producerService.sendMessage(user);
        logger.info("User sent: " + user);
        return ResponseEntity.ok("User sent: " + user);
    }
}
