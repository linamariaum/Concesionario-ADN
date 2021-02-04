package com.ceiba.concessionnaire.infraestructura.persistencia.builder;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;

public final class MotoBuilder {

    private MotoBuilder() {
        throw new IllegalStateException("Clase de traducción");
    }

    public static Moto convertirADominio(MotoEntity motoEntity) {
        Moto moto = null;
        if (motoEntity != null) {
            moto = new Moto(motoEntity.getPlaca(), motoEntity.getMarca(), motoEntity.getModelo(), motoEntity.getColor(), motoEntity.getPrecio());
            moto.setDisponible(motoEntity.isDisponible());
        }
        return moto;
    }

    public static MotoEntity convertirAEntity(Moto moto) {
        MotoEntity motoEntity = new MotoEntity(moto.getPlaca(), moto.getMarca(), moto.getModelo(), moto.getColor(), moto.getPrecio(), moto.isDisponible());
        motoEntity.setPlaca(moto.getPlaca());
        motoEntity.setMarca(moto.getMarca());
        motoEntity.setModelo(moto.getModelo());
        motoEntity.setColor(moto.getColor());
        motoEntity.setPrecio(moto.getPrecio());
        motoEntity.setDisponible(moto.isDisponible());
        return motoEntity;
    }

}
