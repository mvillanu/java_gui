/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.infobosccoma.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxi
 */
public class Model {

    private File file;

    public Model() {

    }

    /**
     * FIlls array with data from a given path
     *
     * @param path
     * @return
     */
    /*
    public List<String> loadData(String path) throws IOException {

        file = new File(path);
        List<String> temp = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        temp
                = Files.readAllLines(Paths.get(file.getCanonicalPath()));

        return temp;
    }
    */
    public TreeMap<String, ArrayList<String>> loadData(String path) throws IOException {
        file = new File(path);
        TreeMap<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();

        if (!file.exists()) {
            file.createNewFile();
        }

        List<String> temp = Files.readAllLines(Paths.get(file.getCanonicalPath()));
        
        for (int i = 0; i < temp.size(); i++) {
            String[] x = temp.get(i).split(";");
            ArrayList<String> values = new ArrayList<String>();
            for (int j = 1; j < x.length; j++) {
                values.add(x[j]);
            }
            map.put(x[0], values);
        }
        //write(map);
        return map;
    }

    public void write(TreeMap<String, ArrayList<String>> data) throws IOException{
            Set set = data.entrySet();
            Iterator i = set.iterator();
            try{
            PrintStream fr = new PrintStream(this.file);
            while(i.hasNext()){
                Map.Entry entry = (Map.Entry)i.next();
                ArrayList<String> wea= (ArrayList<String>)entry.getValue();
                String key =(String)entry.getKey();
                 System.out.print("key: "+ key+ " value[");
                 String value = key+";";
                for(int j=0;j<wea.size();j++){
                    System.out.print(wea.get(j)+", ");
                    if(j<wea.size()-1){
                        value+=wea.get(j)+";";
                    }else{
                        value+=wea.get(j);
                    }
                    
                }
                fr.println(value);
                System.out.println("result: "+value);
                System.out.println();
                
            }
            }catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    /*
    public void write(List<String> data) {
        try {
            PrintStream fr = new PrintStream(file);
            for (int i = 0; i < data.size(); i++) {
                fr.println(data.get(i));
            }
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    public void writel(TreeMap<String, ArrayList<String>> map) {
        Set set = map.entrySet();
        Iterator i = set.iterator();
        try {
            PrintStream fr = new PrintStream(file);
            String csvValues ;
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                ArrayList<String> values = (ArrayList<String>) entry.getValue();
                String data = (String) entry.getKey();
                csvValues = data+";";
                for (int j = 0; j < values.size(); j++) {
                    if (j < values.size() - 1) {
                        csvValues += values.get(0) + ";";
                    } else {
                        csvValues += values.get(0);
                    }  
                }
                fr.println(csvValues);
            }
        } catch (IOException e) {

        }
        

    }

}
