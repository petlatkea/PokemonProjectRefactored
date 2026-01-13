package main.java.opal.pokemon.game.screens.overworld.characters.player;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.CollisionChecker;
import main.java.opal.pokemon.game.screens.overworld.characters.Entity;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.OverWorldController;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel.Direction;

import java.awt.*;
import java.util.Random;

public class Player extends Entity {
    private Random random = new Random();

    private boolean hasChecked;
    boolean sprinting = false;

    // Temporarily kept here - should be moved into the world-model!
    private CollisionChecker collisionChecker;

    public int checkEntityInteraction(Entity entity, Entity[] target) {
        return collisionChecker.checkEntityInteraction(entity, target);
    }

    public Player(GameController gp) {
        super(gp);

        // override model and view with customized versions
        model = new PlayerModel();
        view = new PlayerView(gp, model);

        // TODO: Make Gender an enum with a name
        String gender = gp.genderState == 1 ? "female" : "male";
        view.loadSprites("/images/characters/player/"+gender+"/walk_");

        solidArea = new Rectangle();
        solidArea.x = 33;
        solidArea.y = 49;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 62;
        solidArea.height = 62;

        setDefaultValues();
    }

    private void setDefaultValues() {
        // setting starting positions - TODO: This should be part of the model, and set like every other asset
        model.worldX = 20 * ViewSettings.tileSize;
        model.worldY = 54 * ViewSettings.tileSize;
        model.speed = 3;
        model.direction = Direction.DOWN;
    }

    public void update() {
        /*
        NOTE: The player can only be moved one tile at a time - no matter how long or short the user holds the movement
              key(s) pressed down, the player only stops moving when completely inside a tile.
              When the player is moving, the sprites are animated in a fixed 1,2,1,3,... order, but the animation-frame
              speed is much, much lower than the update speed.
              There is a bit of trickery with stopping the animation - i.e. standing still:
              - this has been completely rewritten
              When the player is moving across grass, calculate the chance of a random occurence of a battle.
         */


        // cast EntityView to PlayerView to make it easier to use ...
        PlayerView view = (PlayerView) this.view;

        // this is the order to do things in
        // 1. check if controls are pressed, and activate movement (in given direction)
        // 2. check if movement in that direction is possible
        // 3. calculate the actual pixels being moved (for this frame)
        //  - stop when the player is entirely within a tile
        // 4. check if walking on grass, and activate a chance battle ... doesn't really belong here, now does it?

        if (model.moving == false) {
            if (gp.getControls().upPressed || gp.getControls().leftPressed || gp.getControls().downPressed || gp.getControls().rightPressed) {
                if (gp.getControls().upPressed) {
                    model.direction = Direction.UP;
                } else if (gp.getControls().leftPressed) {
                    model.direction = Direction.LEFT;
                } else if (gp.getControls().downPressed) {
                    model.direction = Direction.DOWN;
                } else if (gp.getControls().rightPressed) {
                    model.direction = Direction.RIGHT;
                }

                sprinting = gp.getControls().shiftPressed;

                model.moving = true;

                hasChecked = false;

                // CHECK TILE COLLISION
                model.collisionOn = false;
                collisionChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objectIndex = collisionChecker.checkObject(this, true);


                // TODO: Check WHY this is needed here!
                // CHECK NPC COLLISION
                collisionChecker.checkEntityCollision(this, ((OverWorldController) gp.overWorldController).npc);
            } //else {
//                standCounter++;
//                if (standCounter == 20) {
//                    this.view.spriteNum = 1;
//                    standCounter = 0;
//                }
//            }
        }
        int move;
        if (model.moving) {
            collisionChecker.checkGrass(this);
            if (sprinting) {
                move = model.speed * 2;
            } else {
                move = model.speed;
            }
            if (!model.collisionOn) {
                if (view.pixelCounter + move > ViewSettings.tileSize) {
                    move = ViewSettings.tileSize - view.pixelCounter;
                }
                switch (model.direction) {
                    case UP -> model.worldY = model.worldY - move;
                    case LEFT -> model.worldX = model.worldX - move;
                    case DOWN -> model.worldY = model.worldY + move;
                    case RIGHT -> model.worldX = model.worldX + move;
                }
            }

            // The pixelCounter keeps track of how far into a tile the player has moved
            view.pixelCounter = view.pixelCounter + move;

            if (view.pixelCounter >= ViewSettings.tileSize) {
                model.moving = false;
                view.pixelCounter = 0;
                if (model.isGrassOn && hasChecked == false) {
                    view.chance = random.nextInt(10);
                    if (view.chance == 1) {
                        // this changes from playstate into batlestate - but not until the battleintro has completed
                        gp.gameState = GameState.battleIntroState;
                    } else {
                        hasChecked = true;
                    }
                }
            }
        }
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }
}
