/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

/**
 *
 * @author Maxi
 */
public class NoDataFound extends Exception{
    public NoDataFound(){
        super();
    }
    
    public NoDataFound(String text){
        super(text);
    }
}
