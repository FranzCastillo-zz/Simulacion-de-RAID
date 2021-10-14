public class DobleAtaque extends Items{
    private Combatientes emisor;
    public DobleAtaque(Combatientes emisor){
        this.nombre = "Doble Ataque";
        this.emisor = emisor;
    }
    void usarItem(Combatientes receptor){
        emisor.atacar(receptor);
        emisor.atacar(receptor);
    }
}
