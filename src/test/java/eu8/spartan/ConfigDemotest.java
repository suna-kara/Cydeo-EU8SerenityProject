package eu8.spartan;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

public class ConfigDemotest {
    @Test
    public  void  test1(){
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
        System.out.println(ConfigReader.getProperty("spartan.editor.username"));
    }
}
