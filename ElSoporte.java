/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: ElSoporte.java
        Clase que modela la habilidad ElSporte, utilizada por los enemigos
*/
public class ElSoporte extends Habilidades{
    public ElSoporte(){
        this.nombre = "El Soporte";
    }
    
    /** 
     * @param objetivo a quien es el turno de subir la vida, debe mandarse uno por uno
     */
    public void usar(Combatientes objetivo) {
        objetivo.subirVida(5);
    }
}
