package main.java.opal.pokemon.main.model;

import main.java.opal.pokemon.entity.Entity;
import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.OverWorldController;

public class CollisionChecker {
    // TODO: Try to get rid of the controller here!
    private final GameController gp;
    private GameModel model;


    public CollisionChecker(GameController gp, GameModel model) {
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
        int entityWorldX_Center = entity.worldX + entity.solidArea.x + (entity.solidArea.width / 2);
        int entityWorldY_Bottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityCol = entityWorldX_Center / gp.tileSize;
        int entityRow = entityWorldY_Bottom / gp.tileSize;
        boolean walksInGrass = isGrass(model.backgroundTileMap, entityCol, entityRow);

        if(walksInGrass){
            gp.grassSound.playGrassStep();
            entity.isGrassOn = true;

        } else{
            entity.isGrassOn = false;
        }
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;

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
                    entity.collisionOn = true;
                    gp.collisionSound.PlayCollisionSound();

                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

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
                    entity.collisionOn = true;
                    gp.collisionSound.PlayCollisionSound();
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

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
                    entity.collisionOn = true;
                    gp.collisionSound.PlayCollisionSound();

                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

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
                    entity.collisionOn = true;
                    gp.collisionSound.PlayCollisionSound();
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
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                ((OverWorldController)gp.overWorldController).obj[i].solidArea.x = ((OverWorldController)gp.overWorldController).obj[i].worldX + ((OverWorldController)gp.overWorldController).obj[i].solidArea.x;
                ((OverWorldController)gp.overWorldController).obj[i].solidArea.y = ((OverWorldController)gp.overWorldController).obj[i].worldY + ((OverWorldController)gp.overWorldController).obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(((OverWorldController)gp.overWorldController).obj[i].solidArea)) {
                            if (((OverWorldController)gp.overWorldController).obj[i].collision) {
                                entity.collisionOn = true;
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
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
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
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
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
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.getPlayer().solidArea.x = gp.getPlayer().worldX + gp.getPlayer().solidArea.x;
        gp.getPlayer().solidArea.y = gp.getPlayer().worldY + gp.getPlayer().solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.getPlayer().solidArea.x = gp.getPlayer().solidAreaDefaultX;
        gp.getPlayer().solidArea.y = gp.getPlayer().solidAreaDefaultY;
    }
}
