import java.util.ArrayList;

public class RobarVida extends Habilidades{
    public void usar(Combatientes objetivo) {
        //NADA
    }
    public void usar(ArrayList<Combatientes> a) {
        //NO DEBERIA DE HACER NADA
    }
    public void usar(Combatientes emisor, Combatientes receptor){
        int valorAtaque = emisor.getAtaque();
        receptor.bajarVida(valorAtaque);
        emisor.subirVida(valorAtaque);
    }
}
