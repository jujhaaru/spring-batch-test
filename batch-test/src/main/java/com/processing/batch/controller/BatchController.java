package com.processing.batch.controller;

import com.processing.batch.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 5256313
 *
 */
@RestController
@RequestMapping(value = "v1")
@EnableScheduling
public class BatchController {

    @Autowired(required = true)
    private BatchService batchService;

    @GetMapping("apply/gri")
    @Scheduled(cron = "0 */1 * * * ?")
    private ResponseEntity<String> processBatch(){


        List<Long> details = Arrays.asList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l);
        String status=batchService.processJobs(details);
        return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
    }

}
