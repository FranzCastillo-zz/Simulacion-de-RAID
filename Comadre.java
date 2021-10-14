public class Comadre extends Jefe{
    public Comadre(){
        super("Comadre");
        this.vida = 150;
        this.ataque = 15;
        this.habilidad = new RobarVida();
        this.segundaHabilidad = new GolpeLetal();
    }
}
