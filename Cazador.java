/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Cazador.java
        La clase que modela la clase Cazador para los jugadores
*/
public class Cazador extends Jugador {
    public Cazador(String nombre){
        super(nombre);
        this.vida = 150;
        this.ataque = 8;
        inventario.add(new PocionCuracion());
        inventario.add(new PocionCuracion());
        inventario.add(new PocionCuracion());
        inventario.add(new PocionDebilitar());
        this.mascota = new Acompaniantes("Mozzie de " + this.nombre, this);
        mascota.setHabilidad(new RobarVida(mascota));
    }
}
