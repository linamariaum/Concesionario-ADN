package com.ceiba.concessionnaire.infraestructura.persistencia.builder;

import com.ceiba.concessionnaire.dominio.Moto;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;


public final class MotoBuilder {

    public MotoBuilder() {
    }

    public static Moto convertirADominio(MotoEntity motoEntity) {
        Moto moto = null;
        if (motoEntity != null) {
            moto = new Moto(motoEntity.getPlaca(), motoEntity.getMarca(), motoEntity.getModelo(), motoEntity.getColor(), motoEntity.getPrecio());
        }
        return moto;
    }

    public static MotoEntity convertirAEntity(Moto moto) {
        MotoEntity motoEntity = new MotoEntity();
        motoEntity.setPlaca(moto.getPlaca());
        motoEntity.setMarca(moto.getMarca());
        motoEntity.setModelo(moto.getModelo());
        motoEntity.setColor(moto.getColor());
        motoEntity.setPrecio(moto.getPrecio());
        return motoEntity;
    }

}
