/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.infobosccoma.controler;

import Errors.NoDataFound;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.infobosccoma.model.Model;

/**
 *
 * @author Maxi
 */
public class Controller {

    private TreeMap<String, ArrayList<String>> data;
    private Model model;
    private int cursor;
    private Set set;
    private Iterator i;

    public Controller() throws IOException {
        model = new Model();
        cursor = 0;
        data = model.loadData("./test.csv");
        set = data.entrySet();
        i = set.iterator();

    }

    public void edit(String text) {
        String[] temp = text.split(";");
        data.put(temp[0], (ArrayList<String>) Arrays.asList(new String[]{temp[1], temp[2], temp[3]}));
    }

    public int getCursor() {
        return this.cursor;
    }

    public boolean hasPrevious() {
        return cursor > 0;
    }

    public boolean hasNext() {
        return i.hasNext();
    }

    public String[] next() throws NullPointerException {
        String[] result = null;
        if (i.hasNext()) {
            cursor++;
            return getNext();
        }

        return result;
    }

    private String[] getNext() {
        String[] result = new String[5];
        Map.Entry entry = (Map.Entry) i.next();
        ArrayList<String> values = (ArrayList<String>) entry.getValue();
        String data = (String) entry.getKey();
        result[0] = data;
        result[1] = values.get(0);
        result[2] = values.get(1);
        result[3] = values.get(2);
        result[4] = values.get(3);
        return result;
    }

    public int getRegistres() {
        return data.size();
    }

    public String[] previous() throws NullPointerException {
        String[] result = new String[5];

        set = data.entrySet();
        i = set.iterator();

        if (this.hasPrevious()) {
            cursor--;
            for (int j = 0; j <= cursor; j++) {

                if (i.hasNext()) {
                    result = getNext();
                    Arrays.toString(result);
                }
            }
            return result;
        }
        throw new NullPointerException("No further elements to be shown");

    }

    public String[] first() throws NoDataFound {
        String[] result = new String[5];
        try {
            cursor = 0;
            Map.Entry entry = (Map.Entry) data.firstEntry();
            String key = (String) entry.getKey();

            ArrayList<String> values = (ArrayList<String>) entry.getValue();

            result[0] = key;

            for (int j = 0; j < values.size(); j++) {
                result[j + 1] = values.get(j);

            }
            set = data.entrySet();
            i = set.iterator();
            i.next();
            return result;
        } catch (Exception e) {
            cursor = 0;
            throw new NoDataFound("No data was found");
        }

    }

    public void setCursor(int x){
        cursor=x;
    }
    public String[] last() throws NoDataFound {
        //TODO
        String[] result = new String[5];
        try {
            cursor = data.size() - 1;
            
            Map.Entry entry = (Map.Entry) data.lastEntry();
            String key = (String) entry.getKey();
            ArrayList<String> values = (ArrayList<String>) entry.getValue();
            result[0] = key;
            for (int j = 0; j < values.size(); j++) {
                result[j + 1] = values.get(j);
            }
            return result;
        } catch (Exception e) {
            cursor = 0;
            throw new NoDataFound("No data was found");
        }

    }

    public void delete(String dni) {
        data.remove(dni);
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  

    public void save() throws IOException {
        model.write(this.data);
    }

    public void nou(String text) throws IOException {
        String[] temp = text.split(";");
        ArrayList<String> values= new ArrayList<String>();
        for(int j=1;j<temp.length;j++){
            values.add(temp[j]);
        }
        data.put(temp[0], values);
        save();
    }
    
    public boolean exists(String dni){
        return data.containsKey(dni);
    }

}
