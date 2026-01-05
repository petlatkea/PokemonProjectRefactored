package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.ClickHandler;
import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.main.controller.GameState;
import main.java.opal.pokemon.main.controller.PokedexController;
import main.java.opal.pokemon.pokedex.EntryStats;
import main.java.opal.pokemon.pokedex.Pokemon;
import main.java.opal.pokemon.pokedex.TypeEntry;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PokedexScreen extends Screen {
    private PokedexController controller;

    private ClickHandler clickH;

    private boolean showPokedexStartText = true;

    private BufferedImage pokedexBoy, pokedexGirl, searchButtonReleased, searchButtonPressed, previousButtonReleased, nextButtonReleased, previousButtonPressed, nextButtonPressed, onOffButtonOn, onOffButtonOff;

    public PokedexScreen(PokedexController controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void init() {
        loadImages();
        // TODO: Nasty stuff - make the controller handle its own clicks!
        clickH = controller.getGameController().getView().getClickH();
    }

    public void loadImages() {
        UtilityTool uTool = new UtilityTool();
        pokedexBoy = setup("/images/pokedexSprites/boy");
        pokedexGirl = setup("/images/pokedexSprites/girl");

        searchButtonReleased = setup("/images/pokedexSprites/searchPokemonGreen");
        searchButtonPressed = setup("/images/pokedexSprites/searchPokemonOrange");
        previousButtonReleased = setup("/images/pokedexSprites/directionBlueLeft");
        previousButtonPressed = setup("/images/pokedexSprites/directionRedLeft");
        nextButtonReleased = setup("/images/pokedexSprites/directionBlueRight");
        nextButtonPressed = setup("/images/pokedexSprites/directionRedRight");
        onOffButtonOn = setup("/images/pokedexSprites/onOffButtonOn");
        onOffButtonOff = setup("/images/pokedexSprites/onOffButtonOff");
    }

    @Override
    public void drawScreen(Graphics2D g2) {
        // POKEDEX
        int x = 0;
        int y = 0;
        drawPokedex(x, y, pokedexGirl, controller.getGameController().genderState);

        // BUTTONS
        drawButtons();

        // INFO
        if (controller.getPokemon().name != null) {
            showPokedexStartText = false;
            drawPokemonSprite();
            drawPokemonInfo();
        }
        if (showPokedexStartText) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12));
            g2.setColor(Color.black);
            g2.drawString("PÓKEDEX", 695, 396);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 8));
            g2.drawString("Welcome to the International", 655, 376);
            g2.drawString("Search for a Pokémon by", 667, 450);
            g2.drawString("name or number", 697, 465);
        }
        if (clickH.searching) {
            drawCustomInputBox();
        }
    }

    private void drawPokedex(int x, int y, BufferedImage image, int genderState) {
        try {
            if (genderState == 1) {
                image = pokedexGirl;
            }
            if (genderState == 2) {
                image = pokedexBoy;
            }
            g2.drawImage(image, x, y, image.getWidth() * 4, image.getHeight() * 4, null);
            if (!clickH.onOffAction) {
                controller.getGameController().gameState = GameState.playState;
                clickH.onOffAction = true;
                controller.getPokemon().name = null;
                showPokedexStartText = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void drawButtons() {
        int pButtonX = 190;
        int buttonY = 576;
        int nButtonX = 398;
        int width = 147;
        int height = 64;
        int size = 48;
        int sButtonX = 245;
        int onOffX = 605;
        int onOffY = 220;
        int onOffW = 76;
        int onOffH = 70;

        if (clickH.previousButtonPressed) {
            g2.drawImage(previousButtonPressed, pButtonX, buttonY, size, size, null);
        } else {
            g2.drawImage(previousButtonReleased, pButtonX, buttonY, size, size, null);
        }

        if (clickH.searchButtonPressed) {
            g2.drawImage(searchButtonPressed, sButtonX, buttonY - 11, width, height, null);
        } else {
            g2.drawImage(searchButtonReleased, sButtonX, buttonY - 11, width, height, null);
        }

        if (clickH.nextButtonPressed) {
            g2.drawImage(nextButtonPressed, nButtonX, buttonY, size, size, null);
        } else {
            g2.drawImage(nextButtonReleased, nButtonX, buttonY, size, size, null);
        }
        if (clickH.onOff) {
            g2.drawImage(onOffButtonOff, onOffX, onOffY, onOffW, onOffH, null);
        } else {
            g2.drawImage(onOffButtonOn, onOffX, onOffY, onOffW, onOffH, null);
        }
    }

    private void drawPokemonInfo() {
        int x = 625;
        int y = 340;
        final int lineSpace = 15;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 10));
        g2.setColor(Color.black);
        if (controller.pokedex.isSearching()) {
            g2.drawString("Loading...", 700, 675);
            return;
        }

        Pokemon pokemon = controller.getPokemon();

        if (pokemon.getName() == null || pokemon.getId() == 0 || pokemon.getTypes() == null) {
            g2.drawString("No Pokémon Found...", 655, 395);
            g2.drawString("Try again!", 695, 420);
            return;
        }

        // Name
        int xName = getXForCenteredTextAt(pokemon.getName(), 720);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12));
        g2.drawString(pokemon.getName().toUpperCase(), xName, 675);

        // Base info
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 10));
        g2.drawString("NUMBER #" + pokemon.getId(), x, y);
        y += lineSpace;
        g2.drawString("HEIGHT " + String.format("%.1f", pokemon.getHeight() * 0.1) + " M", x, y);
        y += lineSpace;
        g2.drawString("WEIGHT " + String.format("%.1f", pokemon.getWeight() * 0.1) + " KG", x, y);
        y += lineSpace;

        // Pokemon type
        int typeCounter = 1;
        for (TypeEntry entry : pokemon.getTypes()) {
            g2.drawString("TYPE " + typeCounter + ": " + entry.type.name.toUpperCase(), x, y);
            y += lineSpace;
            typeCounter++;
        }
        y += lineSpace;
        // Pokemon Stats
        if (pokemon.getStats() != null) {
            for (EntryStats entry : pokemon.getStats()) {
                g2.drawString(entry.stat.name.toUpperCase() + ": " + entry.base_stat, x, y);
                y += lineSpace;
            }
        }
        // Pokemon description
        y += lineSpace;

        String description = pokemon.getDescription();
        if (pokemon.getDescription() == null) {
            description = "No description foound for " + pokemon.getName();
        }
        g2.drawString("DESCRIPTION: ", x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 9));
        y += lineSpace;
        drawWrappedText(description, x, y, 258, 12);
    }

    private void drawPokemonSprite() {
        int pokemonX = 225;
        int pokemonY = 300;
        int pokemonSize = 96;

        if (controller.pokedex.pokemonSprite != null) {
            g2.drawImage(controller.pokedex.pokemonSprite, pokemonX, pokemonY, pokemonSize * 2, pokemonSize * 2, null);
        }
    }

    private void drawCustomInputBox() {
        // TODO: Fix 'drawingInput' and make local to this controller!
        if (!controller.getGameController().ui.drawingInput) {
            return;
        }

        int boxX = 625;
        int boxY = 645;
        int boxWidth = 250;
        int boxHeight = 40;

        g2.setColor(new Color(100, 117, 113));
        g2.fillRect(boxX, boxY, boxWidth, boxHeight);

        g2.setFont(pkmnFont.deriveFont(Font.BOLD, 12));
        g2.setColor(Color.BLACK);

        int textX = boxX + 10;
        int textY = boxY + boxHeight - 12;
        // TODO: Fix 'inputBuffer' and make local to this controller!
        int xName = getXForCenteredTextAt(controller.getGameController().ui.inputBuffer, 743);
        g2.drawString(controller.getGameController().ui.inputBuffer.toUpperCase(), xName, textY);

        if (controller.getGameController().getDrawCount() % 60 < 30) {
            FontMetrics fm = g2.getFontMetrics();
            int cursorX = xName + (controller.getGameController().ui.inputBuffer.length() * 14);
            g2.drawLine(cursorX, textY - fm.getHeight() + 5, cursorX, textY + 5);
        }
    }

}
