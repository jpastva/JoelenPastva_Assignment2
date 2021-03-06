/**
 * CIS-365 Assignment 2
 * Cappuccino implements Drink interface, stores drink name and recipe,
 * dispenses drink, returns drink cost, checks if in stock.
 *
 * @author Joelen Pastva
 */

import java.text.DecimalFormat;

public class DrinkImplCappuccino implements Drink {
    String name = "Cappuccino";
    String ingredient1 = "Espresso";
    private int ingr1Amt = 2;
    String ingredient2 = "Steamed Milk";
    private int ingr2Amt = 1;
    String ingredient3 = "Foamed Milk";
    private int ingr3Amt = 1;

    public DrinkImplCappuccino() {
    }

    @Override
    public double getDrinkCost() {
        double ingr1Cost = Inventory.getInstance().ingredientsList.get(ingredient1).getCost() * ingr1Amt;
        double ingr2Cost = Inventory.getInstance().ingredientsList.get(ingredient2).getCost() * ingr2Amt;
        double ingr3Cost = Inventory.getInstance().ingredientsList.get(ingredient3).getCost() * ingr3Amt;
        double drinkCost = ingr1Cost + ingr2Cost + ingr3Cost;

        return drinkCost;
    }

    @Override
    public boolean haveInventory() {
        boolean stocked = true;
        int stock1 = Inventory.getInstance().ingredientsList.get(ingredient1).getQuantity();
        int stock2 = Inventory.getInstance().ingredientsList.get(ingredient2).getQuantity();
        int stock3 = Inventory.getInstance().ingredientsList.get(ingredient3).getQuantity();

        if (stock1 < ingr1Amt || stock2 < ingr2Amt || stock3 < ingr3Amt)
            stocked = false;

        return stocked;
    }

    @Override
    public void dispenseDrink() {
        try {
            Inventory.getInstance().useIngredient(ingredient1, ingr1Amt);
            Inventory.getInstance().useIngredient(ingredient2, ingr2Amt);
            Inventory.getInstance().useIngredient(ingredient3, ingr3Amt);
        } catch (InvalidDataException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder drink = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");
        drink.append(name + ", $");
        drink.append(df.format(getDrinkCost()));
        drink.append(", " + haveInventory());

        return drink.toString();
    }
}
