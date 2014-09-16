/* 
 * Copyright (C) 2014 Vasilis Vryniotis <bbriniotis at datumbox.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.datumbox.opensource.examples;

import com.datumbox.opensource.classifiers.NaiveBayes;
import com.datumbox.opensource.dataobjects.NaiveBayesKnowledgeBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Vasilis Vryniotis <bbriniotis at datumbox.com>
 * @see <a
 * href="http://blog.datumbox.com/developing-a-naive-bayes-text-classifier-in-java/">http://blog.datumbox.com/developing-a-naive-bayes-text-classifier-in-java/</a>
 */
public class NaiveBayesExample {

    /**
     * Reads the all lines from a file and places it a String array. In each
     * record in the String array we store a training example text.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String[] readLines(String url) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
        List<String> lines = null;
        try {
            lines = new ArrayList();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[lines.size()]);

    }

    public static String readString(String url) throws IOException {
        List<String> lines;
        StringBuilder builder = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
            lines = new ArrayList();
            builder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Main method
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static NaiveBayes nb = new NaiveBayes();
    String Files[] = new String[]{"UI", "Network", "ImageProcessing", "Embedded", "Database"};
    HashMap<String, Double> maxProbClust = new HashMap<String, Double>();

    public void init() throws IOException {
        Map<String, String> trainingFiles = new HashMap();
        trainingFiles.put("Embedded", "D:\\Dev-Stack\\apache-tomcat-7.0.50\\webapps\\ROOT\\datasets\\embedded.txt");
        trainingFiles.put("Database", "D:\\Dev-Stack\\apache-tomcat-7.0.50\\webapps\\ROOT\\datasets\\database_latest.txt");
        trainingFiles.put("UI", "D:\\Dev-Stack\\apache-tomcat-7.0.50\\webapps\\ROOT\\datasets\\data of UI.txt");
        trainingFiles.put("Network", "D:\\Dev-Stack\\apache-tomcat-7.0.50\\webapps\\ROOT\\datasets\\Data of Network.txt");
        trainingFiles.put("ImageProcessing", "D:\\Dev-Stack\\apache-tomcat-7.0.50\\webapps\\ROOT\\datasets\\data of Image.txt");
        //loading examples in memory
        Map<String, String[]> trainingExamples = new HashMap();
        for (Map.Entry<String, String> entry : trainingFiles.entrySet()) {
            trainingExamples.put(entry.getKey(), readLines(entry.getValue()));

        }

        //train classifier
        nb.setChisquareCriticalValue(6.63); //0.01 pvalue
        nb.train(trainingExamples);
        //get trained classifier knowledgeBase
        NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();
//        System.out.println(knowledgeBase);
        nb = null;
        trainingExamples = null;
        nb = new NaiveBayes(knowledgeBase);

        String exampleEn = "";
        double maxProb = 0;

        for (int i = 0; i < Files.length; i++) {
            for (int j = 1; j < 6; j++) {
                exampleEn = "";
                BufferedReader br = new BufferedReader(new FileReader("D:\\Dev-Stack\\apache-tomcat-7.0.50\\webapps\\ROOT\\Data\\" + Files[i] + "\\" + j + ".txt"));
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    exampleEn = exampleEn + temp;

                }
                String outputEn = nb.predict(exampleEn, "check");
                String[] strAry = exampleEn.split(" ");
                double compare = Double.valueOf(outputEn.substring(outputEn.indexOf(',') + 1));
                if (j == 1) {
                    maxProb = compare;
                } else {
                    if (compare > maxProb) {
                        maxProb = compare / strAry.length;
                    }
                }

            }
            maxProbClust.put(Files[i], maxProb);
        }
        System.out.println("Maxprobcluse : " + maxProbClust);
    }

    public static void main(String[] args) throws IOException {

        NaiveBayesExample ex = new NaiveBayesExample();
        ex.init();

//        ex.doCalculate();
//        //map of dataset files
//        //Use classifier
//        NaiveBayesExample ex = new NaiveBayesExample();
//        ex.init();
////        System.out.println(nb.getKnowledgeBase());
//        String exampleEn =  readString("test.txt");
//        String outputEn = nb.predict(exampleEn,false);
//        LinkedList<Map.Entry<String,Double>> list = new LinkedList<Map.Entry<String,Double>>(nb.rankingMap.entrySet());
//                    Collections.sort(list, new Comparator<Map.Entry<String,Double>>() {
//                        @Override
//                        public int compare(Map.Entry<String, Double> t, Map.Entry<String, Double> t1) {
//                            return t1.getValue().compareTo(t.getValue());
//                        }
//
//                    });
//        System.out.println("Final list "+list);
//        TreeMap<String, Double> finalPercMap = new TreeMap<String, Double>();
//        for(Map.Entry<String,Double> temp : list)
//        {
//            System.out.println("Key : "+temp.getKey());
//            double perc = ex.calculateProbability(temp.getValue(),ex.maxProbClust.get(temp.getKey()));
//            System.out.println("Percentage : "+perc);
//            finalPercMap.put(temp.getKey(), perc);
//        }
//        LinkedList<Map.Entry<String,Double>> list1 = new LinkedList<Map.Entry<String,Double>>(finalPercMap.entrySet());
//                    Collections.sort(list, new Comparator<Map.Entry<String,Double>>() {
//                        @Override
//                        public int compare(Map.Entry<String, Double> t, Map.Entry<String, Double> t1) {
//                            return t.getValue().compareTo(t1.getValue());
//                        }
//
//                    });
//        String json = "{";
//        if(!list.getFirst().getKey().equalsIgnoreCase(list1.getFirst().getKey()))
//        {
//            list1.removeFirst();
//            for(Map.Entry<String,Double> entry : list1)
//            {
//                if(json.length() == 1)
//                    json = json +entry.getKey()+":"+entry.getValue();
//                else
//                    json += ","+entry.getKey()+":"+entry.getValue();
//            }
//           json += "}";
//        }
//        else
//        {
//            Map.Entry<String,Double> dummy = list1.removeFirst();
//            json += dummy.getKey()+":"+dummy.getValue()+"}";
//        }
//        return json;
    }

    public String doCalculate(String filePath) throws IOException {
        //map of dataset files
        //Use classifier
        NaiveBayesExample ex = new NaiveBayesExample();
        ex.init();
//        System.out.println(nb.getKnowledgeBase());
        String exampleEn = readString(filePath);
        String outputEn = nb.predict(exampleEn, false);
        LinkedList<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(nb.rankingMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> t, Map.Entry<String, Double> t1) {
                return t1.getValue().compareTo(t.getValue());
            }

        });
        System.out.println("Final list " + list);
        TreeMap<String, Double> finalPercMap = new TreeMap<String, Double>();
        for (Map.Entry<String, Double> temp : list) {
            System.out.println("Key : " + temp.getKey());
            double perc = ex.calculateProbability(temp.getValue(), ex.maxProbClust.get(temp.getKey()));
            System.out.println("Percentage : " + perc);
            finalPercMap.put(temp.getKey(), perc);
        }
        System.out.println(finalPercMap);

        LinkedList<Map.Entry<String, Double>> list1 = new LinkedList<Map.Entry<String, Double>>(finalPercMap.entrySet());
        Collections.sort(list1, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> t, Map.Entry<String, Double> t1) {
                return t1.getValue().compareTo(t.getValue());
            }

        });
        System.out.println("hi..");
        System.out.println(list1);
        String json = "{";
        if (!list.getFirst().getKey().equalsIgnoreCase(list1.getFirst().getKey())) {
            list1.removeFirst();
            for (Map.Entry<String, Double> entry : list1) {
                if (json.length() == 1) {
                    json = json + "'"+entry.getKey() + "'"+":" + entry.getValue();
                } else {
                    json += "," + "'"+entry.getKey() +"'"+ ":" + entry.getValue();
                }
            }
            json += "}";
        } else {
            Map.Entry<String, Double> dummy = list1.removeFirst();
            json += "'"+dummy.getKey() + "'"+" :"+dummy.getValue() + "}";
        }
        System.out.println("json; " + json);
        return json;
    }

    double calculateProbability(double compareProb, double maxProb) {
//           return ((compareProb - maxProb)/-maxProb)*100;
        if (Math.abs(compareProb) > Math.abs(maxProb)) {
            Double temp = compareProb;
            compareProb = maxProb;
            maxProb = temp;
        }
        return ((compareProb * 100) / maxProb);
    }
}
