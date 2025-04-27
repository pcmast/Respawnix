package com.proyecto3evaluacion.respawnix.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "clave")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClaveWrapper implements Serializable {

    @XmlValue
    private String clave;

        public String getValue() {
            return clave;
        }

        public void setValue(String value) {
            this.clave = value;
        }

}
