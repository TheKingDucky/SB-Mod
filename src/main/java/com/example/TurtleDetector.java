package com.example;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


 // Minimal helper that finds Turtle entities near a player.
 // Returns the list of found turtle entities (client-side instances).

public final class TurtleDetector {
    private TurtleDetector() {}


     // Finds turtles in an axis-aligned box centered on the player.
     //Returns a (mutable) list of found Turtle instances as Entities (so caller can inspect/format).

    public static List<Entity> findTurtlesNearPlayer(Player player, Level level, double radius) {
        if (player == null || level == null) return List.of();

        double px = player.getX();
        double py = player.getY();
        double pz = player.getZ();

        AABB box = new AABB(px - radius, py - radius, pz - radius, px + radius, py + radius, pz + radius);

        // getEntitiesOfClass returns typed mob instances (Turtle). We convert to Entity list to keep API tiny.
        List<Turtle> turtles = level.getEntitiesOfClass(Turtle.class, box, (Predicate<Turtle>) t -> true);
        return new ArrayList<>(turtles);
    }
}