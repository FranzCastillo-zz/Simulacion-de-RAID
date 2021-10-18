/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Terremoto.java
        Clase que modela la habilidad "Terremoto" e implementa su uso
*/
public class Terremoto extends Habilidades{
    private Combatientes emisor;
    public Terremoto(Combatientes emisor){
        this.nombre = "Terremoto";
        this.emisor = emisor;
    }

    
    /** 
     * @param objetivo Cada afectado al que se le bajara la vida, cada indice del array debe ser mandado por separado
     */
    public void usar(Combatientes objetivo) {
        objetivo.bajarVida(emisor.ataque);
    }
}
