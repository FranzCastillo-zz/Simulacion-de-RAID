public class ShotDeFuerza extends Items{
    public ShotDeFuerza(){
        this.nombre = "Shot de Fuerza";
    }
    void usarItem(Combatientes receptor) {
        receptor.modificarAtaque(2);
    }
}
