package main.java.opal.pokemon.game.screens.overworld;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.characters.Entity;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileMap;

public class CollisionChecker {
    // TODO: Try to get rid of the controller here!
    //       Make the CollisionChecker be part of the OverWorldModel (as a composite)
    private final GameController gp;
    private OverWorldModel model;


    public CollisionChecker(GameController gp, OverWorldModel model) {
        this.gp = gp;
        this.model = model;
    }

    private boolean isTileColliding(TileMap map, int col, int row) {
        int tileNum = map.getTileType(col, row);
        return model.getTileType(tileNum).isCollision();
    }

    boolean isGrass(TileMap map, int col, int row) {
        int tileNum = map.getTileType(col,row);
        return model.getTileType(tileNum).isGrass();
    }

    public void checkGrass(Entity entity){
        int entityWorldX_Center = entity.model.worldX + entity.solidArea.x + (entity.solidArea.width / 2);
        int entityWorldY_Bottom = entity.model.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityCol = entityWorldX_Center / ViewSettings.tileSize;
        int entityRow = entityWorldY_Bottom / ViewSettings.tileSize;
        boolean walksInGrass = isGrass(model.backgroundTileMap, entityCol, entityRow);

        if(walksInGrass){
            // TODO: The collisionChecker shouldn't be the one responsible for playing a sound - that should be the controller
            // TODO: Maybe change method to return a boolean ...
            gp.soundController.playGrassStep();
            entity.model.isGrassOn = true;

        } else{
            entity.model.isGrassOn = false;
        }
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.model.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.model.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.model.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.model.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / ViewSettings.tileSize;
        int entityRightCol = entityRightWorldX / ViewSettings.tileSize;
        int entityTopRow = entityTopWorldY / ViewSettings.tileSize;
        int entityBottomRow = entityBottomWorldY / ViewSettings.tileSize;

        switch (entity.model.direction) {
            case UP -> {
                entityTopRow = (entityTopWorldY - entity.model.speed) / ViewSettings.tileSize;

                boolean collidingBackground =
                        isTileColliding(model.backgroundTileMap, entityLeftCol, entityTopRow) ||
                        isTileColliding(model.backgroundTileMap, entityRightCol, entityTopRow);
                boolean collidingEnvironmentB =
                        isTileColliding(model.environmentBTileMap, entityLeftCol, entityTopRow) ||
                        isTileColliding(model.environmentBTileMap, entityRightCol, entityTopRow);
                boolean collidingEnvironmentF =
                        isTileColliding(model.environmentFTileMap, entityLeftCol, entityTopRow) ||
                        isTileColliding(model.environmentFTileMap, entityRightCol, entityTopRow);

                if (collidingBackground || collidingEnvironmentB || collidingEnvironmentF) {
                    entity.model.collisionOn = true;
                    gp.soundController.playCollisionSound();

                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - entity.model.speed) / ViewSettings.tileSize;

                boolean collidingBackground =
                        isTileColliding(model.backgroundTileMap, entityLeftCol, entityTopRow) ||
                        isTileColliding(model.backgroundTileMap, entityLeftCol, entityBottomRow);
                boolean collidingEnvironmentB =
                        isTileColliding(model.environmentBTileMap, entityLeftCol, entityTopRow) ||
                        isTileColliding(model.environmentBTileMap, entityLeftCol, entityBottomRow);
                boolean collidingEnvironmentF =
                        isTileColliding(model.environmentFTileMap, entityLeftCol, entityTopRow) ||
                        isTileColliding(model.environmentFTileMap, entityLeftCol, entityBottomRow);

                if (collidingBackground || collidingEnvironmentB || collidingEnvironmentF) {
                    entity.model.collisionOn = true;
                    gp.soundController.playCollisionSound();
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + entity.model.speed) / ViewSettings.tileSize;

                boolean collidingBackground =
                        isTileColliding(model.backgroundTileMap, entityLeftCol, entityBottomRow) ||
                        isTileColliding(model.backgroundTileMap, entityRightCol, entityBottomRow);
                boolean collidingEnvironmentB =
                        isTileColliding(model.environmentBTileMap, entityLeftCol, entityBottomRow) ||
                        isTileColliding(model.environmentBTileMap, entityRightCol, entityBottomRow);
                boolean collidingEnvironmentF =
                        isTileColliding(model.environmentFTileMap, entityLeftCol, entityBottomRow) ||
                        isTileColliding(model.environmentFTileMap, entityRightCol, entityBottomRow);

                if (collidingBackground || collidingEnvironmentB || collidingEnvironmentF) {
                    entity.model.collisionOn = true;
                    gp.soundController.playCollisionSound();

                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX + entity.model.speed) / ViewSettings.tileSize;

                boolean collidingBackground =
                        isTileColliding(model.backgroundTileMap, entityRightCol, entityTopRow) ||
                        isTileColliding(model.backgroundTileMap, entityRightCol, entityBottomRow);
                boolean collidingEnvironmentB =
                        isTileColliding(model.environmentBTileMap, entityRightCol, entityTopRow) ||
                        isTileColliding(model.environmentBTileMap, entityRightCol, entityBottomRow);
                boolean collidingEnvironmentF =
                        isTileColliding(model.environmentFTileMap, entityRightCol, entityTopRow) ||
                        isTileColliding(model.environmentFTileMap, entityRightCol, entityBottomRow);

                if (collidingBackground || collidingEnvironmentB || collidingEnvironmentF) {
                    entity.model.collisionOn = true;
                    gp.soundController.playCollisionSound();
                }
            }
        }
    }

    // TODO: Extremely messy with the way it uses the obj-array from the controller - but hopefully it will improve,
    //       once the model(s) for the overworld gets more structured!
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < ((OverWorldController)gp.overWorldController).obj.length; i++) {
            if (((OverWorldController)gp.overWorldController).obj[i] != null) {
                entity.solidArea.x = entity.model.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.model.worldY + entity.solidArea.y;

                ((OverWorldController)gp.overWorldController).obj[i].solidArea.x = ((OverWorldController)gp.overWorldController).obj[i].worldX + ((OverWorldController)gp.overWorldController).obj[i].solidArea.x;
                ((OverWorldController)gp.overWorldController).obj[i].solidArea.y = ((OverWorldController)gp.overWorldController).obj[i].worldY + ((OverWorldController)gp.overWorldController).obj[i].solidArea.y;

                switch (entity.model.direction) {
                    case UP:
                        entity.solidArea.y -= entity.model.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.model.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.model.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.model.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.model.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.model.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.model.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.model.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                ((OverWorldController)gp.overWorldController).obj[i].solidArea.x = ((OverWorldController)gp.overWorldController).obj[i].solidAreaDefaultX;
                ((OverWorldController)gp.overWorldController).obj[i].solidArea.y = ((OverWorldController)gp.overWorldController).obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkEntityCollision(Entity entity, Entity[] target) {

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.model.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.model.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].model.worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].model.worldY + target[i].solidArea.y;

                switch (entity.model.direction) {
                    case UP:
                        entity.solidArea.y -= entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.model.collisionOn = true;
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.model.collisionOn = true;
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.model.collisionOn = true;
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.model.collisionOn = true;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
    }

    // TODO: This finds an NPC to interact with, so maybe should be renamed to that?
    //       - or maybe it should be kept generic enough to handle any kind of entity?
    public int checkEntityInteraction(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.model.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.model.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].model.worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].model.worldY + target[i].solidArea.y;

                switch (entity.model.direction) {
                    case UP:
                        entity.solidArea.y -= entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.model.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private void checkPlayer(Entity entity) {
        entity.solidArea.x = entity.model.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.model.worldY + entity.solidArea.y;

        gp.getPlayer().solidArea.x = gp.getPlayer().model.worldX + gp.getPlayer().solidArea.x;
        gp.getPlayer().solidArea.y = gp.getPlayer().model.worldY + gp.getPlayer().solidArea.y;

        switch (entity.model.direction) {
            case UP:
                entity.solidArea.y -= entity.model.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.model.collisionOn = true;
                }
                break;
            case DOWN:
                entity.solidArea.y += entity.model.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.model.collisionOn = true;
                }
                break;
            case LEFT:
                entity.solidArea.x -= entity.model.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.model.collisionOn = true;
                }
                break;
            case RIGHT:
                entity.solidArea.x += entity.model.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.model.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.getPlayer().solidArea.x = gp.getPlayer().solidAreaDefaultX;
        gp.getPlayer().solidArea.y = gp.getPlayer().solidAreaDefaultY;
    }
}
