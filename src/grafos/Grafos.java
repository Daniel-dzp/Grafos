package grafos;

import Archivos.ArchivoMaestro;
import Archivos.ArchivoIndice;
import grafos.Arbol.Arbol;
import grafos.Arbol.Nodo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author dzp
 */
public class Grafos {
    Scanner teclado;
    ArchivoMaestro maestro;
    ArchivoIndice indice;
    Arbol arbol;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Grafos();
    }
    
    public Grafos() throws FileNotFoundException, IOException{
        teclado = new Scanner(System.in);
        maestro = new ArchivoMaestro();
        indice = new ArchivoIndice();
        
        arbol = indice.obtenerIndice();
        
        String entrada;
        
        do{
            System.out.println("\n---------- MENU -----------\n");
            System.out.print("1. Agregar Nodo\n");
            System.out.print("2. Mostrar Nodo(Secuencial indexado)\n");
            System.out.print("3. Mostrar Nodo(Secuencial)\n");
            System.out.print("4. Salir\n");
            System.out.print("OPCION: ");
            
            entrada = teclado.nextLine();
            
            switch(entrada){
                case "1": agregarNodo(); break;
                case "2": mostrarNodoSecuencialIndexado(); break;
                case "3": mostrarNodoSecuencial(); break;
                case "4": System.out.println("  Saliendo..."); break;
            }
            
        }while(!entrada.equals("4"));
        
        indice.cerrar();
        maestro.cerrar();
    }

    private void agregarNodo() throws IOException {
        char nombre;
        String nodos;
        String datos[];
        Estructura estructura;
        int posicion;
        
        System.out.print("Dame nombre nodo: ");
        nombre = teclado.nextLine().charAt(0);
        System.out.print("Dame nodos: ");
        nodos = teclado.nextLine();
        
        datos = nodos.split(" ");
        if(datos.length%2 == 0)
        {
            estructura = new Estructura();
            estructura.nombre = nombre;
            for(int i=0;i<datos.length;i+=2)
            {
                estructura.nodos.add(datos[i].charAt(0));
                estructura.numeros.add(Integer.parseInt(datos[i+1]));
            }
            
            posicion = maestro.agregar(estructura);
            indice.agregar(nombre, posicion);
            
            arbol = indice.obtenerIndice();
        }
        else
            System.out.println("Error al introducir datos");
        
    }
    
    private void mostrarNodoSecuencialIndexado() throws IOException{
        char llave;
        Nodo nodo;
        Estructura estructura;
        
        System.out.print("Dame nombre: ");
        llave = teclado.nextLine().charAt(0);
        
        nodo = arbol.buscar(llave);
        if(nodo != null)
        {
            estructura = maestro.obtener(nodo.direccion);
            
            System.out.println("");
            System.out.println("Llave: "+estructura.clave);
            System.out.println("Nombre: "+estructura.nombre);
            System.out.println("Asociaciones:");
            System.out.println(" Nodo      Valor");
            for(int i=0;i<estructura.nodos.size();i++)
            {  
                System.out.println("  "+estructura.nodos.get(i)+"        "+estructura.numeros.get(i));
            }
        }
        else
            System.out.println("No existe la llave");
        
        
    }
    
    private void mostrarNodoSecuencial() throws IOException{
        int noRegistro;
        Estructura estructura;
        
        System.out.print("Dame no registro: ");
        noRegistro = Integer.parseInt(teclado.nextLine());
        
        estructura = maestro.obtener(noRegistro);
        
        System.out.println("");
        System.out.println("Llave: "+estructura.clave);
        System.out.println("Nombre: "+estructura.nombre);
        System.out.println("Asociaciones:");
        System.out.println(" Nodo      Valor");
        for(int i=0;i<estructura.nodos.size();i++)
        {  
            System.out.println("  "+estructura.nodos.get(i)+"        "+estructura.numeros.get(i));
        }
    }
}
