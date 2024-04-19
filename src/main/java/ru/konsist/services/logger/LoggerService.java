package ru.konsist.services.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService implements ILogger{
    private LoggerService() {
    }

    private static class LoggerHolder {
        public static final LoggerService HOLDER_INSTANCE = new LoggerService();
    }

    public static LoggerService getInstance() {
        return LoggerHolder.HOLDER_INSTANCE;
    }
    public void writeLog(String message){
        try(FileWriter writer = new FileWriter("log.csv", true))
        {
            writer.append(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + "\n");
            writer.append(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
