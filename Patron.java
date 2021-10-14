public class Patron extends Jefe{
    public Patron(){
        super("Patron");
        this.vida = 150;
        this.ataque = 15;
        this.habilidad = new ElSoporte();
        this.segundaHabilidad = new Terremoto();
    }
}
