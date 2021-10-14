import java.util.ArrayList;

public abstract class Habilidades {
    protected String nombre;

    public abstract void usar(Combatientes objetivo);
    public abstract void usar(ArrayList<Combatientes> combatientes);
    public abstract void usar(Combatientes emisor, Combatientes receptor);
}
