/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expression;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Expression {
    public static Scanner sc = new Scanner(System.in);
    public static String psmd(String ex){
        ArrayList<String> exp = new ArrayList<>();
        int j=0;
        for (int i = 0; i < ex.length(); i++) {
            if("+".equals(String.valueOf(ex.charAt(i)))
             ||"-".equals(String.valueOf(ex.charAt(i)))
             ||"*".equals(String.valueOf(ex.charAt(i)))
             ||"/".equals(String.valueOf(ex.charAt(i)))){
            exp.add(ex.substring(j, i));
            exp.add(String.valueOf(ex.charAt(i)));
            j=i+1;
            }            
        }
        exp.add(ex.substring(j));
        
        
        while (exp.size() > 1) {
            boolean nc; 
            
            do {
                nc=false;
                for (int i = 0; i < exp.size()-1; i++) {
                    if ("*".equals(exp.get(i)) || "/".equals(exp.get(i))) {
                        if("*".equals(exp.get(i))){
                            exp.set(i - 1, String.valueOf(Double.parseDouble(exp.get(i - 1))*Double.parseDouble(exp.get(i + 1))));
                            exp.remove(i);
                            exp.remove(i);
                            nc = true;
                            i=i-1;
                        } else if("/".equals(exp.get(i))){
                            exp.set(i - 1, String.valueOf(Double.parseDouble(exp.get(i - 1))/Double.parseDouble(exp.get(i + 1))));
                            exp.remove(i);
                            exp.remove(i);
                            nc = true;
                            i=i-1;
                        }
                    }
                }
            } while (nc == true);
            
            for (int i = 0; i < exp.size()-1; i++) {
                if("+".equals(exp.get(i))){
                    exp.set((i - 1), (String.valueOf(Double.parseDouble(exp.get(i - 1))+Double.parseDouble(exp.get(i + 1)))));
                    exp.remove(i);
                    exp.remove(i);
                    i--;
                }
                else if("-".equals(exp.get(i))){
                    exp.set((i - 1), (String.valueOf(Double.parseDouble(exp.get(i - 1))-Double.parseDouble(exp.get(i + 1)))));
                    exp.remove(i);
                    exp.remove(i);
                    i--;
                }
            }
        }
        return(exp.get(0));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input;
        System.out.print("Enter expression: ");
        input = sc.nextLine();
        int j=0;
        boolean bracket;
        do{
            bracket=false;
        for(int i=0; i<input.length(); i++){
            if("(".equals(String.valueOf(input.charAt(i))))
                j=i;
            if(")".equals(String.valueOf(input.charAt(i)))){
                input=input.substring(0, j) + psmd(input.substring(j+1, i)) + input.substring(i+1);
                bracket=true;
            }
        }
        }while(bracket==true);      
        System.out.println(psmd(input));     
    }
}
