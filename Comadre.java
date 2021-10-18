/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Comadre.java
        Clase que modela al jefe Comadre como combatiente enemigo de los Jugadores y como jefe
*/
public class Comadre extends Jefe{
    public Comadre(){
        super("Comadre");
        this.vida = 150;
        this.ataque = 15;
        this.habilidad = new RobarVida(this);
        this.segundaHabilidad = new GolpeLetal(this);
    }
}
