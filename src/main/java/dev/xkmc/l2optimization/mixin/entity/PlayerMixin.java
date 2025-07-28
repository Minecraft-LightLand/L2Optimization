package dev.xkmc.l2optimization.mixin.entity;

import dev.xkmc.l2optimization.data.PlayerCache;
import dev.xkmc.l2optimization.api.PlayerHook;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerHook {

	@Unique
	private PlayerCache l2optimization$playerCache;

	protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
		super(p_20966_, p_20967_);
	}

	@Override
	public @NotNull PlayerCache l2optimization$getPlayerHook() {
		if (l2optimization$playerCache == null)
			l2optimization$playerCache = new PlayerCache();
		return l2optimization$playerCache;
	}

}
