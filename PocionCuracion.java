public class PocionCuracion extends Items{
    /** 
     * Aumenta en 20 la vida del objetivo
     * @param objetivo a quien se le agregaran 20 pts de vida
     */
    void usarItem(Combatientes objetivo) {
        objetivo.subirVida(20);
    }
    void usarItem(Combatientes emisor, Combatientes receptor) {
        receptor.subirVida(20);
    }
}
