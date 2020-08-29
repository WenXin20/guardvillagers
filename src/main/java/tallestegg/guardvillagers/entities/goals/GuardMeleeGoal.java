package tallestegg.guardvillagers.entities.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.Hand;
import tallestegg.guardvillagers.entities.GuardEntity;

public class GuardMeleeGoal extends MeleeAttackGoal {

    public final GuardEntity guard;

    public GuardMeleeGoal(GuardEntity guard, double speedIn, boolean useLongMemory) {
        super(guard, speedIn, useLongMemory);
        this.guard = guard;
    }

    @Override
    public boolean shouldExecute() {
        return !(this.guard.getHeldItemMainhand().getItem() instanceof CrossbowItem) && super.shouldExecute();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.guard.shieldCoolDown = 0;
    }

    @Override
    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return super.getAttackReachSqr(attackTarget) * 3.55D;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.field_234037_i_ <= 0) {
            this.field_234037_i_ = 20;
            this.attacker.swingArm(Hand.MAIN_HAND);
            this.attacker.attackEntityAsMob(enemy);
            this.attacker.resetActiveHand();
            ((GuardEntity) this.attacker).shieldCoolDown = 10;
        }
    }

}