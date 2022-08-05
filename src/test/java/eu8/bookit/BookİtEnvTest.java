package eu8.bookit;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

public class BookİtEnvTest {

    @Test
    public  void  tets1(){

        System.out.println(ConfigReader.getProperty("base.url"));
        System.out.println(ConfigReader.getProperty("teacher.username"));
        System.out.println(ConfigReader.getProperty("teacher.password"));

    }
}
