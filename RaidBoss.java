import java.util.ArrayList;

public class RaidBoss extends Jefe{
    private ArrayList<Acompaniantes> clones;
    private Liberar liberar;
    private Clonar clonar;
    private Variar variar;
    public RaidBoss(){
        super("INFERNO");
        this.vida = 150;
        this.ataque = 15;
        this.habilidad = new RobarVida(this);
        this.segundaHabilidad = new GolpeLetal(this);
        this.liberar = new Liberar();
        this.clonar = new Clonar();
        this.variar = new Variar();
    }
}
