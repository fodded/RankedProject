package net.rankedproject.gameapi.scheduler;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import net.rankedproject.spigot.CommonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class GameSchedulerContext {

    private final Game game;
    private final IntList scheduledIds = new IntArrayList();

    @CanIgnoreReturnValue
    public BukkitTask run(BiFunction<BukkitScheduler, CommonPlugin, BukkitTask> taskCreator) {
        var task = taskCreator.apply(Bukkit.getScheduler(), game.getPlugin());
        scheduledIds.add(task.getTaskId());
        return task;
    }

    public void stopAll() {
        var scheduler = Bukkit.getScheduler();
        scheduledIds.forEach(scheduler::cancelTask);
    }
}
