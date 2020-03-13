package grafos.Arbol;

/**
 * @author dzp
 */
public class Nodo {
    public char llave;
    public int direccion;
    public Nodo der;
    public Nodo izq;
    
    public Nodo(char llave, int direccion){
        this.llave = llave;
        this.direccion = direccion;
        der = null;
        izq = null;
    }
}
