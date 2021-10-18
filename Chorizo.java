/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Chorizo.java
        Clase que modela al enemigo Chorizo como combatiente enemigo de los Jugadores
*/
public class Chorizo extends Enemigo{
    public Chorizo(){
        super("Chorizo");
        this.vida = 100;
        this.ataque = 10;
        this.habilidad = new RobarVida(this);
    }
}
