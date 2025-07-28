package dev.xkmc.l2optimization.data;

import dev.xkmc.l2optimization.api.PlayerHook;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

public class PlayerCache {

	public static PlayerCache of(Player player) {
		return ((PlayerHook) player).l2optimization$getPlayerHook();
	}

	public final EnderMask mask = new EnderMask();

	public static class EnderMask {

		private boolean skipCache = false;
		private boolean hasResult = false;
		private boolean hasMask = false;
		private int lastCheckTick = -100;

		private void tryCache(Player player, ItemStack mask) {
			if (skipCache) return;
			if (lastCheckTick <= player.tickCount && lastCheckTick >= player.tickCount - 4) return;
			hasResult = false;
			hasMask = false;
			lastCheckTick = player.tickCount;
			try {
				boolean ans = ForgeHooks.shouldSuppressEnderManAnger(null, player, mask);
				hasResult = true;
				hasMask = ans;
			} catch (Throwable ignored) {
				skipCache = true;
			}
		}

		public boolean hasEnderMaskCache(Player player, ItemStack mask) {
			tryCache(player, mask);
			return !skipCache && hasResult;
		}

		public boolean hasEnderMask(Player player) {
			return hasMask;
		}

	}

}
