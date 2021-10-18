/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: dOBLEaTAQUE.java
        Clase que modela el item DobleAtaque para los Jugadores
*/

public class DobleAtaque extends Items{
    private Combatientes emisor;
    public DobleAtaque(Combatientes emisor){
        this.nombre = "Doble Ataque";
        this.emisor = emisor;
    }
    
    /** 
     * @param receptor quien recibira el doble ataque
     */
    void usarItem(Combatientes receptor){
        emisor.atacar(receptor);
        emisor.atacar(receptor);
    }
}
