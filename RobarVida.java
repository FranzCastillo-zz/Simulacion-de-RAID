/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: RobarVida.java
        Clase que modela la habilidad "RobarVida" e implementa su uso
*/
public class RobarVida extends Habilidades{
    Combatientes emisor;
    public RobarVida(Combatientes emisor){
        this.emisor = emisor;
        this.nombre = "Robar vida";
    }
    
    /** 
     * @param objetivo a quien se le quitara la vida para subirla a la instancia que "castea"
     */
    public void usar(Combatientes objetivo) {
        int valorAtaque = emisor.getAtaque();
        objetivo.bajarVida(valorAtaque);
        emisor.subirVida(valorAtaque);
    }
}
