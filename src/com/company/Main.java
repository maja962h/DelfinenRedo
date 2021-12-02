package com.company;

import com.company.data.Database;
import com.company.data.FileHandler;
import com.company.domain.Controller;


public class Main {


    public static void main(String[] Args){
        /*Database db = new Database();
        new FileHandler().readFile();
        System.out.println(db.findMember("Marc"));*/
        new Controller().start();

    }

}
