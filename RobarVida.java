public class RobarVida extends Habilidades{
    Combatientes emisor;
    public RobarVida(Combatientes emisor){
        this.emisor = emisor;
        this.nombre = "Robar vida";
    }
    public void usar(Combatientes objetivo) {
        int valorAtaque = emisor.getAtaque();
        objetivo.bajarVida(valorAtaque);
        emisor.subirVida(valorAtaque);
    }
}
