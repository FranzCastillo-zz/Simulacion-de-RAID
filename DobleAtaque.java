public class DobleAtaque extends Items{

    void usarItem(Combatientes objetivo) {
        // PUES NO DEBERIA DE HACER NADA TEORICAMENTE
    }
    void usarItem(Combatientes emisor, Combatientes receptor) {
        emisor.atacar(receptor);
    }
}
