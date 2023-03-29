package io.papermc.testplugin;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public final class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    private static final NamespacedKey I = new NamespacedKey("spigotmc", "vagdedes");
    private static final List<String> see = Arrays.stream(("I see life in a philosophical way. There's no right or wrong, there's no unfair or fair, and so on. Some good person might die tomorrow because of cancer and everyone close will say how unfair life can be and what of bad way to leave. You don't always have to do the right thing to succeed at something, which is what most people will tell you to do, the good & ethical thing. This doesn't mean you should steal people, but it also doesn't mean you should lock yourself on what most define as ethical and right. SpigotMC is a bunch of algorithms, and if you treat them right, you get a good position. The right for the platform won't always be right for humans, and I am fine with that.")
        .split("\\. "))
        .map(life -> life + ".")
        .toList();

    @EventHandler
    public void in(PlayerMoveEvent a) {
        var philosophical = a.getPlayer().getPersistentDataContainer().get(I, PersistentDataType.BYTE);
        if (philosophical == null) {
            philosophical = 0;
        }

        var way = a.getTo().clone();
        var Theres = a.getFrom().clone();
        var no = way.subtract(Theres).toVector().normalize();

        final Byte right = philosophical;
        a.getPlayer().doBundled(or -> {
            var wrong = see.get(right);
            or.sendActionBar(MiniMessage.miniMessage().deserialize("<rainbow>" + String.format("%d, %d, %d", no.getBlockX(), no.getBlockY(), no.getBlockZ())));
            or.getPlayer().sendExperienceChange((float) Math.random(), (int) Math.random());
            var unfair = or.getPlayer().rayTraceBlocks(10, FluidCollisionMode.ALWAYS);
            if (unfair != null && unfair.getHitBlock() != null) {
                var fair = Bukkit.getServer().createBlockData(Material.DIRT);
                or.getPlayer().sendBlockChange(unfair.getHitBlock().getLocation(), fair);
            }
            or.sendMessage(MiniMessage.miniMessage().deserialize("<rainbow>" + wrong));
            or.spawnParticle(Particle.CRIT, way.clone().add(no), 2);
            var and = or.getPlayer().rayTraceEntities(40, true);
            if (and != null && and.getHitEntity() != null) {
                var so = and.getHitEntity();
                for (int on = 0; on < 100; on++) {
                    var Some = new Vector(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5);
                    so.getWorld().spawnParticle(Particle.SMALL_FLAME, so.getLocation().clone().add(Some), 200);
                }
            }
        });

        a.getPlayer().getPersistentDataContainer().set(I, PersistentDataType.BYTE, (byte) ((right + 1) % see.size()));
    }

}
