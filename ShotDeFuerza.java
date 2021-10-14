public class ShotDeFuerza extends Items{
    void usarItem(Combatientes objetivo) {
        objetivo.modificarAtaque(2);
    }
    void usarItem(Combatientes emisor, Combatientes receptor) {
        receptor.modificarAtaque(2);
    }
}
