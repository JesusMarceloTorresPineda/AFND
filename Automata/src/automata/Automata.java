package automata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Automata {

        Scanner entrada = new Scanner(System.in);
        String deter, entraAux, valida, valida2, lastOne;
        boolean aceptado, pasar=false, fin=false, q2Aceptado, q3aceptado;
        int cont, esParNo=0, contaCaracter=0, restConta=0;
        String[] cadena;
        List<String> al;
        private ArrayList<String> auxCadena = new ArrayList<>();
    public static void main(String[] args) {
        Automata e = new Automata();
        e.iniciar();
        if(e.aceptado)
            System.out.println("Estado de aceptacion");
        else 
            System.out.println("No aceptado");
    }
    
    public void iniciar(){
        cont=0;
        q0();
    }
    
    public void q0(){
        char au;
        aceptado = false;
        System.out.println("Cadena a validar");
        entraAux=entrada.nextLine();
        if(!entraAux.contains(" ")){
            if(entraAux.length() > 1){
                cadena = entraAux.split("");
                al = new ArrayList<String>();
                al = Arrays.asList(cadena);
                for(int i=0;i<cadena.length;i++){
                    au = cadena[i].charAt(0);
                    if(au == 'a' && cadena[i+1].charAt(0) == 'b'){
                        System.out.println("De q0 a q3 = " + au);
                        contaCaracter++;
                        q3();
                        break;
                    }else if(au == 'a' && cadena[i+1].charAt(0) == 'c'){
                        System.out.println("De q0 a q1 = " + au);
                        contaCaracter++;
                        q1();
                        break;
                    }else if(au == 'a' && cadena[i+1].charAt(0) == 'a'){
                        System.out.println("De q0 a q1 = " + au);
                        contaCaracter++;
                        q1();
                        break;
                    }
                }
            }else{
                if(entraAux.charAt(0) == 'a'){
                    System.out.println("De q0 a q3 = "+entraAux);
                    q3aceptado=true;
                    q3();
                }
            }
        }else{
            System.out.println("Cadena no valida, quedo en q0");
            fin=false;
        }   
    }

    public void q1(){
        char errorA;
        for(int i=contaCaracter;i<al.size();i++){
            errorA = al.get(i).charAt(0);
            if(errorA == 'c' && i+1 == al.size()){
                System.out.println("De q1 a q2 = " + errorA);
                q2Aceptado=true;
                q2();
                break;
            }else if(errorA == 'c' && i<al.size()){
                System.out.println("De q1 a q2 = "+ errorA);
                contaCaracter++;
                restConta++;
                q2();
                break;
            }
            else{
                System.out.println("Se murio en q1");
                break;
            }
        }
        
    }
    
    public void q2(){
        char errorB;
        int contaCarAux = contaCaracter;
        if(q2Aceptado){
            aceptado=true;
        }else{
            errorB = al.get(contaCarAux).charAt(0);
            if(errorB == 'c'){
                System.out.println("De q2 a q1 = " + al.get(contaCarAux).toString());
                contaCaracter++;
                restConta++;
                q1();
            }else{
                System.out.println("Cadena invalida, murio en q2");
            }
        }
    }
    
    public void q3(){
        System.out.println("en q3");
        if(q3aceptado){
            aceptado=true;
        }else{
            System.out.println("Camino q0 - q1");
            q3q1();
            System.out.println(" \n< ---------------------------------------------- >\n");
            System.out.println("Camino q3 - q4");
            contaCaracter--;
            q3q4();
        }
    }
    
    public void q3q1(){
        char errorC;
        int contaQ1Q3aux = contaCaracter;
        System.out.println(contaQ1Q3aux);
        errorC = al.get(contaQ1Q3aux).charAt(0);
        if(errorC == 'b'){
            System.out.println("De q3 a q1 = " + al.get(contaQ1Q3aux).toString());
            contaCaracter++;
            q1();
        }else{
            System.out.println("Cadena invalida, murio en q3");
        }
    }
    
    public void q3q4(){
        char errorD;
        int cantQ3Q4aux = contaCaracter-restConta;
        System.out.println(cantQ3Q4aux);
        contaCaracter=cantQ3Q4aux;
        errorD = al.get(cantQ3Q4aux).charAt(0);
        if(errorD == 'b'){
            System.out.println("De q3 a q4 = " + al.get(cantQ3Q4aux).toString());
            contaCaracter++;
            q4();
        }else{
            System.out.println("Cadena invalida, murio en q3");
        }
    }
    
    public void q4(){
        char errorE;
        int cantQ4aux = contaCaracter;
        System.out.println(contaCaracter);
        errorE = al.get(cantQ4aux).charAt(0);
        if(errorE == 'c'){
            System.out.println("De q4 a q2 = " + al.get(cantQ4aux).toString());
            contaCaracter++;
            q2();
        }else{
            System.out.println("Cadena invalida, murio en q4");
        }
    }
    
}