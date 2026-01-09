package main.java.opal.pokemon.game.screens.overworld.characters.player;

import main.java.opal.pokemon.game.screens.overworld.characters.Entity;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.OverWorldController;

import java.awt.*;
import java.util.Random;

public class Player extends Entity {
    private Random random = new Random();

    private boolean hasChecked;
    int standCounter = 0;
    public boolean moving = false;
    boolean sprinting = false;


    public Player(GameController gp) {
        super(gp);

        // override model and view with customized versions
        model = new PlayerModel();
        view = new PlayerView(gp, model);


        solidArea = new Rectangle();
        solidArea.x = 33;
        solidArea.y = 49;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 62;
        solidArea.height = 62;

        setDefaultValues();
        setPlayerImage(this);
    }

    void setPlayerImage(Player player) {
        String gender = switch (player.gp.genderState) {
            case 1 -> "female";
            case 2 -> "male";
            default -> throw new IllegalStateException("No gender with the genderSate: " + player.gp.genderState);
        };

        String path = "/images/characters/player/" + gender + "/";

        view.up1 = view.setup(path + "walk_up_1", player);
        view.up2 = view.setup(path + "walk_up_2", player);
        view.up3 = view.setup(path + "walk_up_3", player);
        view.left1 = view.setup(path + "walk_left_1", player);
        view.left2 = view.setup(path + "walk_left_2", player);
        view.left3 = view.setup(path + "walk_left_3", player);
        view.down1 = view.setup(path + "walk_down_1", player);
        view.down2 = view.setup(path + "walk_down_2", player);
        view.down3 = view.setup(path + "walk_down_3", player);
        view.right1 = view.setup(path + "walk_right_1", player);
        view.right2 = view.setup(path + "walk_right_2", player);
        view.right3 = view.setup(path + "walk_right_3", player);
    }

    private void setDefaultValues() {
        model.worldX = (gp.tileSize * 19) + 32;  // WORLD START POS
        model.worldY = (gp.tileSize * 53) + 16;  // =o=
        model.speed = 3;
        model.direction = "down";
    }

    public void update() {
        // cast EntityView to PlayerView to make it easier to use ...
        PlayerView view = (PlayerView) this.view;
        if (moving == false) {
            if (gp.getControls().upPressed || gp.getControls().leftPressed || gp.getControls().downPressed || gp.getControls().rightPressed) {
                if (gp.getControls().upPressed) {
                    model.direction = "up";
                } else if (gp.getControls().leftPressed) {
                    model.direction = "left";
                } else if (gp.getControls().downPressed) {
                    model.direction = "down";
                } else if (gp.getControls().rightPressed) {
                    model.direction = "right";
                }

                sprinting = gp.getControls().shiftPressed;

                moving = true;
                hasChecked = false;

                // CHECK TILE COLLISION
                model.collisionOn = false;
                gp.cChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objectIndex = gp.cChecker.checkObject(this, true);


                // TODO: Check WHY this is needed here!
                // CHECK NPC COLLISION
                gp.cChecker.checkEntityCollision(this, ((OverWorldController) gp.overWorldController).npc);
            } else {
                standCounter++;
                if (standCounter == 20) {
                    this.view.spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
        int move;
        if (moving) {
            gp.cChecker.checkGrass(this);
            if (sprinting) {
                move = model.speed * 2;
            } else {
                move = model.speed;
            }
            if (!model.collisionOn) {
                if (view.pixelCounter + move > gp.tileSize) {
                    move = gp.tileSize - view.pixelCounter;
                }
                switch (model.direction) {
                    case "up" -> model.worldY = model.worldY - move;
                    case "left" -> model.worldX = model.worldX - move;
                    case "down" -> model.worldY = model.worldY + move;
                    case "right" -> model.worldX = model.worldX + move;
                }
            }

            this.view.spriteCounter++;

            if (this.view.spriteCounter > 10) {
                view.orderIndex = (view.orderIndex + 1) % view.spriteOrder.length;
                this.view.spriteNum = view.spriteOrder[view.orderIndex];
                this.view.spriteCounter = 0;
            }

            view.pixelCounter = view.pixelCounter + move;

            if (view.pixelCounter >= gp.tileSize) {
                moving = false;
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

}
