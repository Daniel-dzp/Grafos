package Archivos;

import grafos.Estructura;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchivoMaestro {
    RandomAccessFile archivo;
    StringBuffer buffer = null;
    
    public ArchivoMaestro() throws FileNotFoundException{
        archivo = new RandomAccessFile("maestro.bin","rw");
    }
        
    public int agregar(Estructura estructura) throws IOException{
        archivo.seek(archivo.length());
        
        estructura.clave = (int)archivo.length()/246+1;
        archivo.writeInt(estructura.clave);
        archivo.writeChar(estructura.nombre);
        
        for(int i=0;i<40;i++)
        {
            if(i<estructura.nodos.size())
            {
                archivo.writeChar(estructura.nodos.get(i));
                archivo.writeInt(estructura.numeros.get(i));
            }
            else
            {
                archivo.writeChar('\0');
                archivo.writeInt(0);
            }
        }
        
        return estructura.clave;
    }
    
    public Estructura obtener(int posicion) throws IOException {
        Estructura estructura = new Estructura();
        char nombre;
        int valor;
        
        if(archivo.length() > ((posicion-1)*246))
        {
            archivo.seek((posicion-1)*246);
            
            estructura.clave = archivo.readInt();
            estructura.nombre = archivo.readChar();
            
            for (int i = 0; i<40;i++)
            {
                nombre = archivo.readChar();
                valor = archivo.readInt();
                if(nombre != '\0')
                {
                    estructura.nodos.add(nombre);
                    estructura.numeros.add(valor);
                }
            }
        }
        
        return estructura;
    }
    
    public void cerrar() throws IOException
    {
        archivo.close();
    }
    
}
