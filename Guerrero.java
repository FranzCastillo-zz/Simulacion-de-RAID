public class Guerrero extends Jugador{
    public Guerrero(String nombre){
        super(nombre);
        this.vida = 250;
        this.ataque = 20;
        this.mascota = null;
        inventario.add(new PocionCuracion());
        inventario.add(new ShotDeFuerza());
        inventario.add(new PocionDebilitar());
        inventario.add(new PocionDebilitar());
        inventario.add(new DobleAtaque(this));
        inventario.add(new DobleAtaque(this));
        inventario.add(new NuevosMusculos());
    }
}
