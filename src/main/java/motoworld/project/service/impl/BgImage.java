package motoworld.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BgImage {
    private final Logger LOGGER = LoggerFactory.getLogger(BgImage.class);
    private boolean day = true;

    public String getBgDay() {
        return "/images/bgDay.jpg";
    }

    public String getBgNight() {
        return "/images/bgNight.jpg";
    }

    public String getActive() {

        if (day) {
            return getBgDay();
        } else {
            return getBgNight();
        }

    }

    public void swapActive() {
        day = !day;
    }

    @Scheduled(cron = "50 * * * * *")
    public void onEachMinute() {
        LOGGER.info("Daily Image Swapped! It should swap every day one" +
                " image for the morning, and one image for the night," +
                " but for the demo is on every minute at :50sec :)");
        swapActive();
    }

}
