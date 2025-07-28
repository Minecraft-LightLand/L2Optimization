package dev.xkmc.l2optimization.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.xkmc.l2optimization.data.PlayerCache;
import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderMan.class)
public class EnderManMixin {

	@WrapOperation(method = "isLookingAtMe", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;shouldSuppressEnderManAnger(Lnet/minecraft/world/entity/monster/EnderMan;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Z"))
	private boolean l2optimization$isMask(EnderMan enderMan, Player player, ItemStack mask, Operation<Boolean> original) {
		if (L2OConfig.COMMON.doEnderMaskCache.get()) {
			var data = PlayerCache.of(player);
			if (data.mask.hasEnderMaskCache(player, mask)) {
				return data.mask.hasEnderMask(player);
			}
		}
		return original.call(enderMan, player, mask);
	}

}
