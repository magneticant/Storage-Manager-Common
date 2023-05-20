//package domain;
package rs.np.storage_manager_common.domain;

/**
 * Enumeracija za tip proizvoda
 * 
 * @author Milan
 * 
 * @since 1.0.0
 */
public enum ProductType {
	/**
	 * akcesoari za kuvanje (zacini, zejtin, posudje, tiganj itd.)
	 */
    Cooking,
    /**
     * sredstva za ciscenje (moze biti i sredstvo za WC, kuhinju, ciscenje prasine ili kamenca itd.)
     */
    Cleaning,
    /**
     * bilo koji tip alata 
     */
    Tool, 
    /**
     * sve sto spada u voce
     */
    Fruit, 
    /**
     * sve sto spada u povrce
     */
    Vegatable, 
    /**
     * crveno i belo meso
     */
    Meat, 
    /**
     * iskljucivo mleko
     */
    Dairy, 
    /**
     * mlecni proizvodi (jogurt, pavlaka, sir, kiselo mleko itd.) zajedno sa jajima
     */
    MilkAndEggs,
    /**
     * namestaj (dvosedi, trosedi, kreveti, stolice, police, ormani, lezaljke itd.)
     */
    Furniture, 
    /**
     * televizori
     */
    TV, 
    /**
     * laptopovi
     */
    Laptop, 
    /**
     * telefoni
     */
    Phone, 
    /**
     * mikrotalasne pecnice
     */
    Microwave, 
    /**
     * rerne (pecnice)
     */
    Stove, 
    /**
     * umetnicka dela (uglavnom ulje na platnu)
     */
    Art, 
    /**
     * akcesoari za kucne ljubimce (hrana, orglice, lekovi itd.)
     */
    Pets, 
    /**
     * tepisi
     */
    Carpets,
    /**
     * akcesoari za automobile (osvezivac vazduha, nasloni za sedista itd.)
     */
    CarAccesory, 
    /**
     * ukrasi (ukrasi za jelku, ukrasne vestacke biljke, ukrasne statue itd.)
     */
    Decoration, 
    /**
     * modni akcesoari
     */
    Fashion, 
    /**
     * odeca u opstem smislu (polovna i nova)
     */
    Clothing, 
    /**
     * parfemi i dezodoransi
     */
    Perfume, 
    /**
     * lampe i ostali vidovi osvetljenja
     */
    Lamp, 
    /**
     * igracke (barbi lutke, drustvene igre, automobili za decu, rubikove kocke, zvecke, lego kockice itd.)
     */
    Toy, 
    /**
     * hrana (u opstem smislu, koja ne odgovara ostatku nabrojanog, mogu biti grickalice, secerna vuna,
     * oblanda, semenke, proteinski dodaci ishrani itd.)
     */
    Food, 
    /**
     * sve ostalo
     */
    Other
}
