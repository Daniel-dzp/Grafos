package grafos;

import java.util.ArrayList;

public class Estructura {
    public int clave;
    public char nombre;
    public ArrayList<Character> nodos;
    public ArrayList<Integer> numeros;
    
    public Estructura(){
        nodos = new ArrayList();
        numeros = new ArrayList();
    }
}