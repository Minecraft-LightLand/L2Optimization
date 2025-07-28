package dev.xkmc.l2optimization.mixin.vehicle;

import net.minecraft.server.level.ChunkMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkMap.TrackedEntity.class)
public class TrackedEntityMixin {

	@Shadow
	@Final
	private int range;

	@Inject(method = "getEffectiveRange", cancellable = true, at = @At("HEAD"))
	public void l2optimization$getEffectiveRange(CallbackInfoReturnable<Integer> cir) {
		if (range >= 32 * 16) {
			cir.setReturnValue(range);
		}
	}

}
