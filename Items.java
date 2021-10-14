abstract class Items {
    protected String nombre;

    public String getNombre(){
        return this.nombre;
    }
    abstract void usarItem(Combatientes receptor);
}
