/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: K70.java
        Clase que modela al enemigo K-70 como combatiente enemigo de los Jugadores
*/
public class K70 extends Enemigo{
    public K70(){
        super("K-70");
        this.vida = 120;
        this.ataque = 8;
        this.habilidad = new GolpeLetal(this);
    }
}
