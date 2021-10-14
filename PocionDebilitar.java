public class PocionDebilitar extends Items{
    
    /** 
     * Disminuye en 5pts la capacidad de ataque del objetivo
     * @param objetivo a quien se le disminuira el ataque
     */
    void usarItem(Combatientes objetivo) {
        objetivo.modificarAtaque(-5);
    }
    
    void usarItem(Combatientes emisor, Combatientes receptor) {
        receptor.modificarAtaque(-5);
    }
}
