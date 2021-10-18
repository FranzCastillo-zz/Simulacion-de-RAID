/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: GolpeLetal.java
        Clase que modela la habilidad Golpe Letal, utilizada por los enemigos
*/

public class GolpeLetal extends Habilidades{
    private Combatientes emisor;
    public GolpeLetal(Combatientes emisor){
        this.nombre = "Golpe Letal";
        this.emisor = emisor;
    }
    
    /** 
     * @param objetivo contra quien se utilizara la habilidad
     */
    public void usar(Combatientes objetivo) {
        objetivo.bajarVida(emisor.getAtaque() + 10);
    }
}
