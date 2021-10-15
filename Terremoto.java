public class Terremoto extends Habilidades{
    private Combatientes emisor;
    public Terremoto(Combatientes emisor){
        this.nombre = "Terremoto";
        this.emisor = emisor;
    }

    public void usar(Combatientes objetivo) {
        objetivo.bajarVida(emisor.ataque);
    }
}
