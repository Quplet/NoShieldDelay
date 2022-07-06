package qu.noshielddelay.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import qu.noshielddelay.config.ModConfig;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    //Injects into the isBlocking method and changes the 5 constant to whatever ModConfig.DELAY is.
    @ModifyConstant(method = "isBlocking", constant = @Constant(intValue = 5))
    private int setShieldUseDelay(int constant) {
        if (ModConfig.ENABLED) return ModConfig.DELAY;
        return constant;
    }
}
