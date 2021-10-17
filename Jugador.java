import java.util.ArrayList;

public class Jugador extends Combatientes {
    protected ArrayList<Items> inventario;
    protected Acompaniantes mascota;

    public Jugador(String nombre){
        super(nombre);
        this.inventario = new ArrayList<>();
        this.mascota = null;
    }
    public ArrayList<Items> getInventario(){
        return this.inventario;
    }
    public Acompaniantes getMascota(){
        return this.mascota;
    }
}
