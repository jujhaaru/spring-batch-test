package com.processing.batch.service;

import java.util.List;

public interface BatchService {

    String processJobs(List<Long> details);
}
