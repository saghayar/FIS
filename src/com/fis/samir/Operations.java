/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fis.samir;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Samir
 */
public class Operations {

    List result = new ArrayList();
    List dataList = new ArrayList();
    private Double res[][];
    private final Double u[] = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
    List listMin = new ArrayList();
    List listAvg = new ArrayList();
    double[] res2 = new double[11];

    public static double min(double a, double b) {
        return Math.min(a, b);
    }

    public static double min(double a, double b, double c) {
        return min(Math.min(a, b), c);
    }

    public static double min(double a, double b, double c, double d) {
        return min(min(a, b, c), d);
    }

    public List reflectPiece(List data, float start, float end, float size) {
        List list = new ArrayList();
        float elementD;
        for (int index = 0; index <= size; index++) {
            if (index < data.size()) {
                elementD = Float.parseFloat(data.get(index).toString());
                list.add((float) ((elementD - start) / (end - start)));
            }
        }
        return list;
    }

    public double average() {
        double sum = 0.0;
        double average;
        int count = 0;
        for (int i = 0; i < u.length; i++) {
            if (res2[i] != 1000) {
                count++;
                sum += u[i];
            }
        }
        average = sum / count;
        return average;
    }

    public static Double getMinValue(double[] array) {
        Double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public static Double getMaxValue(double[] array) {
        Double maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != 1000) {
                if (array[i] > maxValue) {
                    maxValue = array[i];
                }
            }
        }
        return maxValue;
    }

    public static String getMinValueAndIndex(double[] array) {
        Double minValue = array[0];
        int k = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 1000.0) {
            } else if (array[i] < minValue) {
                minValue = array[i];
                k = i / 10;
            }
        }
        return minValue + ":" + k;
    }

    private double calcIntegral(double max) {
        double sum = 0.0;
        for (int i = 0; i < listMin.size(); i++) {
            sum += Double.valueOf(listMin.get(i).toString()) * Double.valueOf(listAvg.get(i).toString());
        }
        listAvg.clear();
        listMin.clear();
        return sum / max;
    }

    private void printMas(double[] res1) {
        System.out.println("====begin=======");
        for (int i = 0; i < res1.length; i++) {
            String string = String.valueOf(res1[i]);
            System.out.println(string);
        }
        System.out.println("====end========");
    }

    private void printMas(Object[][] res1) {
        System.out.println("====begin=======");
        for (int i = 0; i < res1.length; i++) {
            for (int j = 0; j < res1[0].length; j++) {
                String string = String.valueOf(res1[i][j]);
                System.out.println(string);
            }

        }
        System.out.println("====end========");
    }

    private List pieceForGaussian(List data) {
        List listLing = new ArrayList();
        for (int k = 0; k < data.size() - 1; k++) {
            float ff = (float) (Float.valueOf(data.get(k).toString()) + Float.valueOf(data.get(k + 1).toString())) / 2;
            listLing.add(ff);
        }
        return listLing;
    }

    private boolean is1000(double[] mas) {
        boolean b = false;
        for (double ma : mas) {
            if (ma != 1000) {
                b = true;
            }
        }
        return b;
    }

    private double[] getDataFromStr(List list) {
        double[] d = new double[11];
        for (int i = 0; i < list.size(); i++) {
            d[i] = Double.parseDouble(list.get(i).toString());
        }

        return d;
    }

    private void cleanSameValueOfArray(String min) {
        if (min.length() != 0) {
            for (String ss : min.split(":")) {
                for (int i = 0; i < res2.length; i++) {
                    if (ss.equals(String.valueOf(res2[i]))) {
                        res2[i] = 1000;
                    }
                }
            }
        }
    }

    private int distinctValueCount(double array[]) {
        int distinctIntegers = 0;
        for (int j = 0; j < array.length; j++) {
            double thisInt = array[j];

            boolean seenThisIntBefore = false;

            for (int i = 0; i < j; i++) {
                if (thisInt == array[i]) {
                    seenThisIntBefore = true;
                }
            }
            if (!seenThisIntBefore) {
                distinctIntegers++;
            }
        }
        return distinctIntegers;
    }

    public void calculateGaussianMemFunc(List data) {
        double[] sigma = {0.4, 0.45, 0.35, 0.3};
        double dd;
        res = new Double[sigma.length][data.size()];
        List gausMem = pieceForGaussian(data);
        for (int j = 0; j < sigma.length; j++) {
            for (int i = 0; i < gausMem.size(); i++) {
                // qauss funk qiymeti
                dd = 1 / Math.exp((Math.pow(Double.valueOf(gausMem.get(i).toString()), 2)) / Math.pow(sigma[j], 2));
                res[j][i] = dd;
            }
        }
        getMins(res);
    }

    //sira onemli !!!
    private List getMins(Double[][] res) {
        List l = new ArrayList();
        String str = "";
        //1 2       
        for (int j = 0; j < res[0].length - 1; j++) {
            str += ":" + Math.min(res[0][j], res[1][j]);
            if (j == (res[0].length - 2)) {
                l.add(str);
                str = "";
            }

        }

        // 1 2 3
        for (int j = 0; j < res[0].length - 1; j++) {
            str += ":" + min(res[0][j], res[1][j], res[2][j]);
            if (j == res[0].length - 2) {
                l.add(str);
                str = "";
            }
        }
        //1 2 3 4
        for (int j = 0; j < res[0].length - 1; j++) {
            str += ":" + min(res[0][j], res[1][j], res[2][j], res[3][j]);
            if (j == res[0].length - 2) {
                l.add(str);
                str = "";
            }
        }

        // 2 3 4
        for (int j = 0; j < res[0].length - 1; j++) {
            str += ":" + min(res[1][j], res[2][j], res[3][j]);
            if (j == res[0].length - 2) {
                l.add(str);
                str = "";
            }
        }
        // 1 3 -4
        for (int j = 0; j < res[0].length - 1; j++) {
            str += ":" + min(res[0][j], res[2][j], 1 - res[3][j]);
            if (j == res[0].length - 2) {
                l.add(str);
                str = "";
            }
        }

        // -3 -4
        for (int j = 0; j < res[0].length - 1; j++) {
            str += ":" + min(1 - res[2][j], 1 - res[3][j]);
            if (j == res[0].length - 2) {
                l.add(str);
                str = "";
            }
        }
        calculateMatrixWithLucasevic(l);
        return l;
    }

    private void calculateMatrixWithLucasevic(List l) {
        int i = 1;
        List l1 = new ArrayList();
        for (Iterator it = l.iterator(); it.hasNext(); ) {
            Object s = it.next();
            String str = s.toString();
//            System.out.println("=======R " + i + "=======");
            for (int j = 1; j < str.split(":").length; j++) {
                String ss = str.split(":")[j];
                for (int k = 0; k <= 10; k++) {
                    if (!calc(i, u[k]).equals("null")) {
                        Double dd = Double.valueOf(calc(i, u[k]));
                        Double d = Double.valueOf(ss.trim());
//                       System.out.println(Math.min(1.0, 1.0 - d + dd));
                        l1.add(Math.min(1.0, 1.0 - d + dd));
                    }
                }
            }
            i++;
        }
        findMinValue(l1);

    }

    private String calc(int i, Double x) {
        switch (i) {
            case 1:
                return x.toString();
            case 2:
                return String.valueOf(Math.sqrt(x));
            case 3:
                return String.valueOf((x == 1) ? 1 : ((x < 1) ? 0 : "null"));
            case 4:
                return String.valueOf(Math.pow(x, 2));
            case 5:
                return x.toString();
            case 6:
                return String.valueOf((1 - x));
            default:
                return "null";
        }

    }
//yekun  R in qiymeti

    private void findMinValue(List l) {
        BufferedWriter bw = null;
        String path = null;
        try {
            path = System.getProperty("user.home") + "/Desktop/FIS.txt";
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            bw = new BufferedWriter(new FileWriter(f, true));
            double[] lasrRes = new double[6];
            int ind = 0;
            System.out.println("SIZE" + l.size());
            for (int i = 0; i < 55; i++) {
                for (int j = i, h = 0; j < l.size(); j += 55, h++) {
                    lasrRes[h] = Double.valueOf(l.get(j).toString());
                }
                double ff = getMinValue(lasrRes);
                dataList.add(ff);
                bw.append(ff + "   ");
                ind++;
                if (ind % 11 == 0) {
                    bw.newLine();
                    ind = 0;
                }
            }
            findMinEk();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Programi yeniden yukleyin");
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Programi yeniden yukleyin");
            }
        }

    }
//Beyin xarab eliyen yer

    private void findMinEk() {
        String memo = "";
        double min = 0.0;
        double temp = 0.0;
        for (int ind = 0; ind < dataList.size() / 11; ind++) {
            temp = 0.0;
            res2 = getDataFromStr(dataList.subList(11 * ind, 11 * (ind + 1)));
            double max = getMaxValue(res2);
            int limit = distinctValueCount(res2);
            for (int i = 0; i < limit; i++) {
                min = getMinValue(res2) - temp;
                temp = getMinValue(res2);
                memo += temp + ":";
                double avg = average();
//                System.out.println("ind = " + ind + "  i =" + i + " delta = " + min + " M(E) = " + avg + " limit  " + limit);
                listMin.add(min);
                listAvg.add(avg);
                cleanSameValueOfArray(memo);
            }
            double resD = calcIntegral(max);
            result.add(resD);
        }
        System.out.println(result);
    }
}
