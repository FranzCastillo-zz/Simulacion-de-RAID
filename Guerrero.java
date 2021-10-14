public class Guerrero extends Jugador{
    public Guerrero(String nombre){
        super(nombre);
        this.vida = 150;
        this.ataque = 20;
        inventario.add(new PocionCuracion());
        inventario.add(new PocionDebilitar());
        inventario.add(new PocionDebilitar());
        inventario.add(new DobleAtaque());
        inventario.add(new DobleAtaque());
        inventario.add(new NuevosMusculos());
    }
}
