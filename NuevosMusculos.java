/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: NuevosMusculos.java
        Clase que modela al enemigo Chorizo como combatiente enemigo de los Jugadores
*/
public class NuevosMusculos extends Items {
    public NuevosMusculos(){
        this.nombre = "Nuevos Musculos";
    }
    
    /** 
     * @param receptor quien recibira el aumento de 5pts de ataque
     */
    void usarItem(Combatientes receptor) {
        receptor.modificarAtaque(5);
    }
}
