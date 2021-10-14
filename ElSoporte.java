import java.util.ArrayList;

public class ElSoporte extends Habilidades{
    public void usar(Combatientes objetivo) {
        
    }
    public void usar(ArrayList<Combatientes> combatientes) {
        for (Combatientes combatiente : combatientes) {
            combatiente.subirVida(5);
        }
    }
    public void usar(Combatientes emisor, Combatientes receptor) {
        
    }
    
}
