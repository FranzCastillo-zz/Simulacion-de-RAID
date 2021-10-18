/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Items.java
        Clase abstracta que modela todos los items de los jugadores y asegura que siempre tengan una forma de utilizarse
*/
abstract class Items {
    protected String nombre;

    /** 
     * @return String el nombre del item
     */
    public String getNombre(){
        return this.nombre;
    }
    abstract void usarItem(Combatientes receptor);
}
