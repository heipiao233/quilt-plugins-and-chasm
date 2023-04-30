package net.heipiao.qpac.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.heipiao.qpac.QPAC;
import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Inject(method = "init", at = @At("TAIL"))
	public void exampleMod$onInit(CallbackInfo ci) {
		QPAC.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
