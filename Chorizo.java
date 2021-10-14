public class Chorizo extends Enemigo{
    public Chorizo(){
        super("Chorizo");
        this.vida = 100;
        this.ataque = 10;
        this.habilidad = new RobarVida();
    }
    
}
