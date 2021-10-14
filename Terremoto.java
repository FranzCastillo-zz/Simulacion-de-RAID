import java.util.ArrayList;

public class Terremoto extends Habilidades{

    public void usar(Combatientes objetivo) {
        // NADA
    }

    public void usar(ArrayList<Combatientes> combatientes){
        for (Combatientes combatiente : combatientes) {
            combatiente.bajarVida(10);
        }
    }

    public void usar(Combatientes emisor, Combatientes receptor){
        // NADA
    }
}
