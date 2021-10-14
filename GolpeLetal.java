import java.util.ArrayList;

public class GolpeLetal extends Habilidades{
    public void usar(Combatientes objetivo) {
        //NADA
    }
    public void usar(ArrayList<Combatientes> combatientes) {
        //NADA
    }
    public void usar(Combatientes emisor, Combatientes receptor) {
        receptor.bajarVida(emisor.getAtaque() + 10);
    }
    
}
