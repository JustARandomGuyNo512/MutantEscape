package mutantescape.tools;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RenderTools {
    public static double getRenderValueUIWidth(double UiWidth,double value,double valuemax){
        return (value/valuemax)*UiWidth;
    }

    public static Vec3 getLivingEntityLookDirection(LivingEntity livingEntity) {
        float yaw = livingEntity.getYRot();
        float pitch = livingEntity.getXRot();

        float yawRad = -yaw * ((float) Math.PI / 180F);
        float pitchRad = -pitch * ((float) Math.PI / 180F);


        double x = Math.cos(yawRad) * Math.cos(pitchRad);
        double y = Math.sin(pitchRad);
        double z = Math.sin(yawRad) * Math.cos(pitchRad);

        return new Vec3(x, y, z);
    }
    public static Vec3 getLivingEntityLookOppositeDirection(LivingEntity livingEntity) {
        Vec3 lookDirection = getLivingEntityLookDirection(livingEntity);
        return lookDirection.scale(-1);
    }

    public static List<Vec3> LivingEntitRoundPos(LivingEntity livingEntity, double MaxPos, double min, int max){
        List<Vec3> coordinates = new ArrayList<>();
        double angleStep=MaxPos/360;
        for (int i = 0; i < MaxPos; i++) {
            double angle = i * angleStep;
            double radians = Math.toRadians(angle);
            double offsetX = Mth.cos((float) radians);
            double offsetZ = Mth.sin((float) radians);
            Vec3 newCoordinate = new Vec3(livingEntity.getBlockX() + offsetX, livingEntity.getBlockY(), livingEntity.getBlockZ() + offsetZ);
            coordinates.add(newCoordinate);
        }
        return coordinates;

    }







}
