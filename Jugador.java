import java.util.ArrayList;

public class Jugador extends Combatientes {
    protected ArrayList<Items> inventario;

    public Jugador(String nombre){
        super(nombre);
        this.inventario = new ArrayList<>();
    }
    public ArrayList<Items> getInventario(){
        return this.inventario;
    }
    public void usarItem(int posicion){
        //inventario.get(posicion).usar();
    }
}
