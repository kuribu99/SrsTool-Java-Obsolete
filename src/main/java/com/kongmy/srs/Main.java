/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.srs;

import com.kongmy.core.Application;

/**
 *
 * @author Kong My
 */
public class Main {

    public static void main(String[] args) {
        Application app = SrsApplication.bootstrap();
        app.run();
    }

}
