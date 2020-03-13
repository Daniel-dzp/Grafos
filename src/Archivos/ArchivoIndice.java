package Archivos;

import grafos.Arbol.Arbol;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchivoIndice {
    RandomAccessFile archivo;
    StringBuffer buffer = null;
    int noBloque = 0;
    
    public ArchivoIndice() throws FileNotFoundException{
        archivo = new RandomAccessFile("indice.bin","rw");
    }
    
    public void agregar(char llave, int posicion) throws IOException{
        archivo.seek(archivo.length());
        archivo.writeChar(llave);
        
        archivo.writeInt(posicion);
        
        noBloque++;
    }
    
    public int buscar(int llaveBuscar) throws IOException {
        char llave = '\0';
        int posicion = -1;
        long longitudArchivo;
        
        archivo.seek(0);
        longitudArchivo = archivo.length();
        
        for(int i=0;(i<longitudArchivo/8) && (llave != llaveBuscar);i++)
        {
            llave = archivo.readChar();
            posicion = archivo.readInt();
        }
        
        if(llave == llaveBuscar)
            return posicion;
        else
            return -1;
    }

    public Arbol obtenerIndice() throws IOException {
        char llave;
        int posicion;
        long longitudArchivo;
        Arbol arbol = new Arbol();
        
        archivo.seek(0);
        longitudArchivo = archivo.length();
        
        for(int i=0;i<longitudArchivo/6;i++)
        {
            llave = archivo.readChar();
            posicion = archivo.readInt();
            arbol.agregar(llave, posicion);
        }
        
        return arbol;
    }
    
    public void cerrar() throws IOException
    {
        archivo.close();
    }
}
