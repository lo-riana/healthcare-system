package payment;

public class CouvertureAssurance extends VariationPrix {
    private float tauxCouverture;

    @Override
    public void appliquer() {
        // logique d'application de la couverture d'assurance
    }

    public float getTauxCouverture() { 
        return tauxCouverture; 
    }
}
