package com.example.service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {
    @Scheduled(cron = "0 */1 * * * *")
    @SchedulerLock(
            name = "generateReports",
            lockAtLeastFor = "30s",
            lockAtMostFor = "5m"
    )
    public void generateReports() {

        System.out.println("Running report generation : "
                + Thread.currentThread().getName());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
