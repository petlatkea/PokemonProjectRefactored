package main.java.opal.pokemon.game.screens.overworld;

import main.java.opal.pokemon.game.screens.overworld.characters.npc.NPC;
import main.java.opal.pokemon.game.screens.overworld.characters.player.Player;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.ScreenController;
import main.java.opal.pokemon.game.screens.dialogue.DialogueController;
import main.java.opal.pokemon.game.input.MouseClick;
import main.java.opal.pokemon.game.input.Controls;
import main.java.opal.pokemon.game.screens.overworld.objects.SuperObject;

public class OverWorldController extends ScreenController {

    private OverWorldModel model;

    // TODO: Split player and NPCs into model, view and controllers, and add separately
    private Player player;
    public NPC[] npc = new NPC[20];
    public SuperObject[] obj = new SuperObject[10];

    private Camera camera;

    // TODO: Make this part of the model-initialization for the overworld - setting all the NPCs
    private AssetSetter assetSetter;

    public OverWorldController(GameController gameController) {
        super(gameController);
        // create model and view
        model = new OverWorldModel(gameController);
        player = new Player(gameController);
        // temporarily let the player itself own a collision-checker - should probably be in the overworldmodel ...
        player.setCollisionChecker( new CollisionChecker(gameController, model));

        screen = new OverWorldScreen(this, model);
        // load assets to the view
        assetSetter = new AssetSetter(gameController);
        assetSetter.setObject(obj);
        assetSetter.setNPC(npc);

        camera = new Camera();

    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() { return camera; }

    @Override
    public void update() {
        player.update();
        gameController.soundController.updateMusic(player); // NOTE: used for updating the music for the zone
        // TODO: This should probably be handled by the GameController calling update directly on the soundController
        gameController.soundController.updateFade();

        // NOTE: No NPCs implement .update() - so maybe get rid of it?
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
            }
        }

        camera.centerOn(player);
    }

    @Override
    public void keyPressed(int keyCode) {
        Controls controls = gameController.getControls();

        if (controls.pokedexPressed) {
            gameController.openPokedex();
        } else if (controls.escapePressed) {
            gameController.gameState = GameState.pauseState;
        } else if (controls.ePressed) {
            // Interact with NPC - if possible
            NPC npc = getNPCforInteraction();
            if (npc != null) {
                interactWithNPC(npc);
            }
        }
    }

    private NPC getNPCforInteraction() {
        int npcIndex = player.checkEntityInteraction(player, npc);
        // if there is an NPC (other than 999 == no NPC) - return that
        if (npcIndex != 999) {
            return npc[npcIndex];
        }
        return null;
    }

    private void interactWithNPC(NPC npc) {
        // ask the dialogue-controller to initiate dialogue with this NPC
        ((DialogueController) gameController.dialogueController).initiateDialogueWithNPC(npc);
    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // pokedex icon
        if (mouseClick.insideBox(40, 696, 44, 58)) {
            gameController.openPokedex();
        }
    }
}
