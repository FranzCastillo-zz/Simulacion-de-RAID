public class NuevosMusculos extends Items {
    public NuevosMusculos(){
        this.nombre = "Nuevos Musculos";
    }
    void usarItem(Combatientes receptor) {
        receptor.modificarAtaque(5);
    }
}
