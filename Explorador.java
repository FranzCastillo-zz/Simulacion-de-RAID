public class Explorador extends Jugador{
    public Explorador(String nombre){
        super(nombre);
        this.vida = 100;
        this.ataque = 10;
        inventario.add(new PocionCuracion());
        inventario.add(new PocionCuracion());
        inventario.add(new PocionCuracion());
        inventario.add(new PocionDebilitar());
        inventario.add(new PocionDebilitar());
        inventario.add(new PocionDebilitar());
        inventario.add(new ShotDeFuerza());
        inventario.add(new ShotDeFuerza());
        inventario.add(new DobleAtaque(this));
        inventario.add(new DobleAtaque(this));
        inventario.add(new NuevosMusculos());
    }
}
