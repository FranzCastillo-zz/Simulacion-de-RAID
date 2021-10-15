public class GolpeLetal extends Habilidades{
    private Combatientes emisor;
    public GolpeLetal(Combatientes emisor){
        this.nombre = "Golpe Letal";
        this.emisor = emisor;
    }
    public void usar(Combatientes objetivo) {
        objetivo.bajarVida(emisor.getAtaque() + 10);
    }
}
