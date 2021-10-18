/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: PocionCuracion.java
        Clase que modela el item "PoionCuracion" e implementa su uso
*/
public class PocionCuracion extends Items{
    public PocionCuracion(){
        this.nombre = "Pocion de Curacion";
    }
    
    /** 
     * @param receptor quien recibira 15pts de vida
     */
    void usarItem(Combatientes receptor) {
        receptor.subirVida(15);
    }
}
