public class Cazador extends Jugador {
    public Cazador(String nombre){
        super(nombre);
        this.vida = 70;
        this.ataque = 8;
        inventario.add(new PocionCuracion());
        inventario.add(new PocionCuracion());
        inventario.add(new PocionCuracion());
        inventario.add(new PocionDebilitar());
    }

    public void invocarMascota(String nombre){
        //new Acompaniante
    }
}
