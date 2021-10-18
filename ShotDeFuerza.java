/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: ShotDeFuerza.java
        Clase que modela el item "ShotDeFuerza" e implementa su uso
*/
public class ShotDeFuerza extends Items{
    public ShotDeFuerza(){
        this.nombre = "Shot de Fuerza";
    }
    
    /** 
     * @param receptor quien recibira un aumento en 2pts de ataque
     */
    void usarItem(Combatientes receptor) {
        receptor.modificarAtaque(2);
    }
}
