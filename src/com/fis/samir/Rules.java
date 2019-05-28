/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fis.samir;

/**
 *
 * @author Samir
 */
public class Rules {

    public static String doChoose(String x1, String x2, String x3, String x4) {
        if (x1.equals(LinguisticVariables.NOT_OBSERVED.toString()) && x2.equals(LinguisticVariables.LOW.toString())
                && x3.equals("") && x4.equals("")) {
            return LinguisticVariables.SATISFACTORY.toString();
        } else if (x1.equals(LinguisticVariables.NOT_OBSERVED.toString()) && x2.equals(LinguisticVariables.LOW.toString())
                && x3.equals(LinguisticVariables.NOT_OBSERVED.toString()) && x4.equals("")) {
            return LinguisticVariables.MORE_THEN_SATISFACTORY.toString();
        }
        if (x1.equals(LinguisticVariables.NOT_OBSERVED.toString()) && x2.equals(LinguisticVariables.LOW.toString())
                && x3.equals(LinguisticVariables.NOT_OBSERVED.toString()) && x4.equals(LinguisticVariables.NOT_OBSERVED.toString())) {
            return LinguisticVariables.PERFECT.toString();
        }
        if (x2.equals(LinguisticVariables.LOW.toString()) && x3.equals(LinguisticVariables.NOT_OBSERVED.toString())
                && x4.equals(LinguisticVariables.NOT_OBSERVED.toString())) {
            return LinguisticVariables.VERY_SATISFACTORY.toString();
        }
        if (x1.equals(LinguisticVariables.NOT_OBSERVED.toString()) && x3.equals(LinguisticVariables.NOT_OBSERVED.toString())
                && x4.equals(LinguisticVariables.OBSERVED.toString())) {
            return LinguisticVariables.SATISFACTORY.toString();
        }
        if (x3.equals(LinguisticVariables.OBSERVED.toString()) && x4.equals(LinguisticVariables.OBSERVED.toString())) {
            return LinguisticVariables.UNSATISFACTORY.toString();
        } else {
            return null;
        }

    }

//    public static void main(String[] args) {
//        System.out.println(new Rules().doChoose(LinguisticVariables.NOT_OBSERVED.toString(), LinguisticVariables.LOW.toString(), "", ""));
//    }
}
