public class PocionCuracion extends Items{
    public PocionCuracion(){
        this.nombre = "Pocion de Curacion";
    }
    void usarItem(Combatientes receptor) {
        receptor.subirVida(15);
    }
}
