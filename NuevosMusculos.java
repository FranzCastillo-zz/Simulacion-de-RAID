public class NuevosMusculos extends Items {
    /** 
     * Aumenta en 5 el valor de ataque del objetivo
     * @param objetivo a quien se le subira 5pts de ataque
     */
    void usarItem(Combatientes objetivo) {
        objetivo.modificarAtaque(5);
    }
    void usarItem(Combatientes emisor, Combatientes receptor) {
        receptor.modificarAtaque(5);
    }
}
