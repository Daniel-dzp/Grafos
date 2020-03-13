package grafos.Arbol;

/**
 * @author dzp
 */
public class Arbol {
    public Nodo raiz;
    
    public Arbol(){
        raiz = null;
    }
    
    public void agregar(char llave, int direccion)
    {
        Nodo r;
        Nodo ant;
        
        if(raiz == null)
            raiz = new Nodo(llave, direccion);
        else
            _agregar(llave, direccion, raiz);
    }
    
    private void _agregar(char llave, int direccion, Nodo r)
    {
        if(r.llave > llave)
            if(r.izq == null)
                r.izq = new Nodo(llave, direccion);
            else
                _agregar(llave, direccion, r.izq);
        else
            if(r.der == null)
                r.der = new Nodo(llave, direccion);
            else
                _agregar(llave, direccion, r.der);
    }
    
    public Nodo buscar(char llave)
    {
        if(raiz != null)
            return _buscar(llave, raiz);
        else
            return null;
    }
    
    private Nodo _buscar(char llave, Nodo r){
        if(r == null)
            return null;
        else
            if(r.llave == llave)
                return r;
            else
                if(llave > r.llave)
                    return _buscar(llave, r.der);
                else
                    return _buscar(llave, r.izq);
    }
    
    public void mostrar(Nodo r){
        if(r != null)
        {
            mostrar(r.izq);
            System.out.println(r.llave);
            mostrar(r.der);
        }
    }
}
