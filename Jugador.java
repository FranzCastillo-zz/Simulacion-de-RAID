/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Jugador.java
        Clase que modela todos los jugadores
*/

import java.util.ArrayList;

public class Jugador extends Combatientes {
    protected ArrayList<Items> inventario;
    protected Acompaniantes mascota;

    public Jugador(String nombre){
        super(nombre);
        this.inventario = new ArrayList<>();
        this.mascota = null;
    }
    
    /** 
     * @return ArrayList<Items> el inventario de esta instancia
     */
    public ArrayList<Items> getInventario(){
        return this.inventario;
    }
    
    /** 
     * @return Acompaniantes la mascota asociada a esta instancia (Si no es Cazador, devuelve null)
     */
    public Acompaniantes getMascota(){
        return this.mascota;
    }
}
