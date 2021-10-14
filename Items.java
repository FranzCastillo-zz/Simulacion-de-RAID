abstract class Items {
    protected String nombre;

    public String getNombre(){
        return this.nombre;
    }

    abstract void usarItem(Combatientes objetivo);
    abstract void usarItem(Combatientes emisor, Combatientes receptor);
}
