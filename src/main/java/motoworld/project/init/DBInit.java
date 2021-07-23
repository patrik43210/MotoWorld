package motoworld.project.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final Init dbInit;

    public DBInit( Init dbInit) {
        this.dbInit = dbInit;

    }


    @Override
    public void run(String... args) throws Exception {

     dbInit.seedAdmin();
     dbInit.seedMotorcycle();
     dbInit.seedPlace();
    }


}
