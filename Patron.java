/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Patron.java
        Clase que modela al tipo de jefe "Patron"
*/
public class Patron extends Jefe{
    public Patron(){
        super("Patron");
        this.vida = 150;
        this.ataque = 15;
        this.habilidad = new ElSoporte();
        this.segundaHabilidad = new Terremoto(this);
    }
}
