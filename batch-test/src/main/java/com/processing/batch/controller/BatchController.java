package com.processing.batch.controller;

import com.processing.batch.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class BatchController {

    @Autowired(required = true)
    private BatchService batchService;

    @GetMapping("apply/gri")
    private ResponseEntity<String> processBatch(){


        List<Integer> details = Arrays.asList(1, 2, 3, 4, 4, 5, 5, 6, 7, 8, 0);
        String status=batchService.processJobs(details);
        return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
    }

}
