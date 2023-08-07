package neogenesis.entities.comp;

import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import neogenesis.annotations.Annotations.*;
import neogenesis.types.CustomUnitType;
import neogenesis.entities.*;
import neogenesis.entities.Rotor.*;
import neogenesis.types.*;

import static mindustry.Vars.*;

/**
 * @author GlennFolker
 * @author MEEPofFaith
 */
@SuppressWarnings("unused")
@EntityComponent
public abstract class Copterc implements Unitc{
    transient RotorMount[] rotors;
    transient float rotorSpeedScl = 1f;

    @Import UnitType type;
    @Import boolean dead;
    @Import float health, rotation;
    @Import int id;

    @Override
    public void add(){
        CustomUnitType type = (CustomUnitType)this.type;

        rotors = new RotorMount[type.rotors.size];
        for(int i = 0; i < rotors.length; i++){
            Rotor rotor = type.rotors.get(i);
            rotors[i] = new RotorMount(rotor);
            rotors[i].rotorRot = rotor.rotOffset;
            rotors[i].rotorShadeRot = rotor.rotOffset;
        }
    }

    @Override
    public void update(){
        CustomUnitType type = (CustomUnitType)this.type;
        if(dead || health < 0f){
            if(!net.client() || isLocal()) rotation += type.fallRotateSpeed * Mathf.signs[id % 2] * Time.delta;

            rotorSpeedScl = Mathf.lerpDelta(rotorSpeedScl, 0f, type.rotorDeathSlowdown);
        }else{
            rotorSpeedScl = Mathf.lerpDelta(rotorSpeedScl, 1f, type.rotorDeathSlowdown);
        }

        for(RotorMount rotor : rotors){
            rotor.rotorRot += rotor.rotor.speed * rotorSpeedScl * Time.delta;
            rotor.rotorRot %= 360f;

            rotor.rotorShadeRot += rotor.rotor.shadeSpeed * Time.delta;
            rotor.rotorShadeRot %= 360f;
        }
    }
}