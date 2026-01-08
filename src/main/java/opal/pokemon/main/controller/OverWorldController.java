package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.entity.Entity;
import main.java.opal.pokemon.entity.Player;
import main.java.opal.pokemon.main.AssetSetter;
import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.model.Controls;
import main.java.opal.pokemon.main.view.OverWorldScreen;
import main.java.opal.pokemon.object.SuperObject;

public class OverWorldController extends ScreenController {

    // TODO: Split player and NPCs into model, view and controllers, and add separately
    private Player player;
    public Entity[] npc = new Entity[20];
    public SuperObject[] obj = new SuperObject[10];

    // TODO: Make this part of the model-initialization for the overworld - setting all the NPCs
    private AssetSetter assetSetter;

    public OverWorldController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        player = new Player(gameController);
        screen = new OverWorldScreen(this);

        assetSetter = new AssetSetter(gameController);
        assetSetter.setObject(obj);
        assetSetter.setNPC(npc);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        player.update();
        gameController.music.updateMusic(player); // NOTE: used for updating the music for the zone
        gameController.music.updateFade();

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
            }
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        Controls controls = gameController.getControls();

        if (controls.pokedexPressed) {
            gameController.openPokedex();
        } else if(controls.escapePressed) {
            gameController.gameState =  GameState.pauseState;
        } else if(controls.ePressed) {
            // Interact with NPC - if possible
            Entity npc = getNPCforInteraction();
            if (npc != null) {
                interactWithNPC(npc);
            }
        }
    }

    private Entity getNPCforInteraction() {
        int npcIndex = gameController.cChecker.checkEntityInteraction(player, npc);
        // if there is an NPC (other than 999 == no NPC) - return that
        if (npcIndex != 999) {
            return npc[npcIndex];
        }
        return null;
    }

    private void interactWithNPC(Entity npc) {
        // ask the dialogue-controller to initiate dialogue with this NPC
        ((DialogueController)gameController.dialogueController).initiateDialogueWithNPC(npc);
    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // pokedex icon
        if (mouseClick.insideBox(40, 696, 44, 58)) {
            gameController.openPokedex();
        }
    }
}
