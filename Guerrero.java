public class Guerrero extends Jugador{
    public Guerrero(String nombre){
        super(nombre);
        this.vida = 150;
        this.ataque = 20;
        inventario.add(new PocionCuracion());
        inventario.add(new ShotDeFuerza());
        inventario.add(new PocionDebilitar());
        inventario.add(new PocionDebilitar());
        inventario.add(new DobleAtaque(this));
        inventario.add(new DobleAtaque(this));
        inventario.add(new NuevosMusculos());
    }
}
